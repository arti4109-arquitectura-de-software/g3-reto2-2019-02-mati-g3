package com.atpos.api.commons.exception;

public class ChecksumInvalidityException extends RuntimeException {
    public ChecksumInvalidityException() { super(); }
    public ChecksumInvalidityException(String message) { super(message); }
    public ChecksumInvalidityException(String message, Throwable cause) { super(message, cause); }
    public ChecksumInvalidityException(Throwable cause) { super(cause); }
}
