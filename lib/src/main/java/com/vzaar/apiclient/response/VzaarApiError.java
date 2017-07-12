package com.vzaar.apiclient.response;

@SuppressWarnings("unused")
public class VzaarApiError {
    private String message;
    private String detail;

    private VzaarApiError() {
    }

    public String getDetail() {
        return detail;
    }

    public String getMessage() {
        return message;
    }

    public String toString() {
        return message + " ( " + detail + " ) ";
    }

}
