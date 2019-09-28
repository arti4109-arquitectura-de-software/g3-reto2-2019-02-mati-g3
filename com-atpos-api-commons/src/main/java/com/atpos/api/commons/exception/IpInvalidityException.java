package com.atpos.api.commons.exception;

public class IpInvalidityException extends RuntimeException {
    public IpInvalidityException() { super(); }
    public IpInvalidityException(String message) { super(message); }
    public IpInvalidityException(String message, Throwable cause) { super(message, cause); }
    public IpInvalidityException(Throwable cause) { super(cause); }
}
