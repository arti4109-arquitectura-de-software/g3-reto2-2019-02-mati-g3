package com.atpos.api.ws.config;


import com.atpos.api.business.service.impl.IpVerifyServiceImpl;
import com.atpos.api.commons.exception.ChecksumInvalidityException;
import com.atpos.api.commons.exception.IpInvalidityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@ControllerAdvice
public class CustomRequestBodyAdviceAdapter extends RequestBodyAdviceAdapter {

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    private IpVerifyServiceImpl ipVerifyServiceImpl;

    @Override
    public boolean supports(MethodParameter methodParameter, Type type,
                            Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage,
                                MethodParameter parameter, Type targetType,
                                Class<? extends HttpMessageConverter<?>> converterType) {

        String ip = getClientIP();

        if (ipVerifyServiceImpl.isBlocked(ip)) {
            throw new IpInvalidityException("Blocked IP");
        }

        String checksum = getChecksum();

        String bodyChecksum = "";

        try {
            MessageDigest md  = MessageDigest.getInstance("MD5");
            md.update(body.toString().getBytes());
            byte[] digest = md.digest();
            bodyChecksum = DatatypeConverter.printHexBinary(digest).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        if(!bodyChecksum.equalsIgnoreCase(checksum)){
            ipVerifyServiceImpl.ipFailed(ip);
            throw new ChecksumInvalidityException("Message Integrity  Error");
        }

        ipVerifyServiceImpl.ipSucceeded(ip);

        return super.afterBodyRead(body, inputMessage, parameter, targetType, converterType);
    }


    private String getClientIP() {
        String xfHeader = httpServletRequest.getHeader("X-Forwarded-For");
        if (xfHeader == null){
            return httpServletRequest.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }


    private String getChecksum() {
        String csHeader = httpServletRequest.getHeader("X-Checksum");
        if (csHeader == null){
            throw new ChecksumInvalidityException("Message Checksum not present in header");
        }
        return csHeader;
    }
}
