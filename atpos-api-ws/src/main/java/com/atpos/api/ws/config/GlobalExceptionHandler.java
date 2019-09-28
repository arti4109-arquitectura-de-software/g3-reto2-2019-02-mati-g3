package com.atpos.api.ws.config;

import com.atpos.api.commons.exception.ChecksumInvalidityException;
import com.atpos.api.commons.exception.IpInvalidityException;
import com.atpos.api.commons.util.Utils;
import com.atpos.api.domain.dto.ResponseDTO;
import com.atpos.api.domain.dto.StatusDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

import static com.atpos.api.commons.costant.Constants.*;


@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler( value = {MissingServletRequestParameterException.class})
    public final ResponseEntity<ResponseDTO> exceptionHandlerIllegalRequestParameterException(final MissingServletRequestParameterException e) {
        return buildFailure(BAD_PROCESS_CODE, e.getMessage(), null);
    }

    @ExceptionHandler( value = {IllegalArgumentException.class})
    public final ResponseEntity<ResponseDTO> exceptionHandlerIllegalArgumentException(final IllegalArgumentException e) {
        return buildFailure(BAD_PROCESS_CODE, e.getMessage(), null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<ResponseDTO> handleValidationExceptions(final MethodArgumentNotValidException e) {

        log.error(SERVER_ERROR_MESSAGE  + Utils.getStackTrace(e) + SERVER_ERROR_MESSAGE + e.getMessage());

        String error = e.getBindingResult()
                .getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining(","));

        return buildFailure(BAD_PROCESS_CODE, error, e.getMessage());
    }

    @ExceptionHandler({RuntimeException.class })
    public final ResponseEntity<ResponseDTO> handleGeneralException(final Exception e) {
        log.error(SERVER_ERROR_MESSAGE  + Utils.getStackTrace(e) + SERVER_ERROR_MESSAGE + e.getMessage());
        return buildFailure(SERVER_ERROR_CODE, SERVER_ERROR_MESSAGE, e.getMessage());
    }

    @ExceptionHandler({IpInvalidityException.class, ChecksumInvalidityException.class})
    public final ResponseEntity<ResponseDTO> handleAppRuntimeException(final Exception e) {
        log.error(SERVER_ERROR_MESSAGE  + Utils.getStackTrace(e) + SERVER_ERROR_MESSAGE + e.getMessage());
        return buildFailure(BAD_PROCESS_CODE, e.getMessage(), null);
    }

    private ResponseEntity<ResponseDTO> buildFailure(String code, String message, String detailedMessage) {
        ResponseDTO response = ResponseDTO.builder().status(new StatusDTO(code,  message , detailedMessage)).build();
        return new ResponseEntity<ResponseDTO>(response, new HttpHeaders(), HttpStatus.OK);
    }
}

