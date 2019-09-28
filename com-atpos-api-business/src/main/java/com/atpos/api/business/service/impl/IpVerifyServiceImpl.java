package com.atpos.api.business.service.impl;

import com.atpos.api.business.service.IpVerifyService;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service
public class IpVerifyServiceImpl  implements IpVerifyService {

    private final int MAX_ATTEMPT = 3;
    private LoadingCache<String, Integer> attemptsCache;

    public IpVerifyServiceImpl() {
        super();
        attemptsCache = CacheBuilder.newBuilder().
                expireAfterWrite(1, TimeUnit.DAYS).build(new CacheLoader<String, Integer>() {
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
            return attemptsCache.get(ip) >= MAX_ATTEMPT;
        } catch (ExecutionException e) {
            return false;
        }
    }
}
