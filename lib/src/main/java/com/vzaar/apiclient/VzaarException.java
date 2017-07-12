package com.vzaar.apiclient;

public class VzaarException extends RuntimeException {
    public VzaarException(String reason) {
        super(reason);
    }

    public VzaarException(String reason, Throwable throwable) {
        super(reason, throwable);
    }

}
