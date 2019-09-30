package com.atpos.api.business.service.impl;

import com.atpos.api.business.service.IpVerifyService;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class IpVerifyServiceImpl  implements IpVerifyService {

    private final int MAX_ATTEMPT = 3;
    private LoadingCache<String, Integer> attemptsCache;

    public IpVerifyServiceImpl() {
        super();
        attemptsCache = CacheBuilder.newBuilder().
                expireAfterWrite(1, TimeUnit.HOURS).build(new CacheLoader<String, Integer>() {
            public Integer load(String ip) {
                return 0;
            }
        });
    }

    @Override
    public void ipSucceeded(String ip) {
        attemptsCache.invalidate(ip);
    }

    @Override
    public void ipFailed(String ip) {
        int attempts = 0;
        try {
            attempts = attemptsCache.get(ip);
        } catch (ExecutionException e) {
            attempts = 0;
        }
        attempts++;
        attemptsCache.put(ip, attempts);
    }

    @Override
    public boolean isBlocked(String ip) {
        try {
            boolean isBlocked = attemptsCache.get(ip) >= MAX_ATTEMPT;
            if(isBlocked){
                log.error("The IP --> " + ip + " is Blocked");
            }
            return isBlocked;
        } catch (ExecutionException e) {
            return false;
        }
    }
}
