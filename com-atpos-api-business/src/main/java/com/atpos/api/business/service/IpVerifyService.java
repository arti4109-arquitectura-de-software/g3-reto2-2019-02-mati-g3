package com.atpos.api.business.service;

public interface IpVerifyService {

    void ipSucceeded(String ip);

    void ipFailed(String ip);

    boolean isBlocked(String ip);
}
