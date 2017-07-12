package com.vzaar.apiclient.response;

import com.vzaar.apiclient.VzaarException;

import java.util.List;

@SuppressWarnings({"CanBeFinal", "unused"})
public class VzaarResponse<T> {

    private T data;
    private List<VzaarApiError> errors;

    public T getData() {
        if (!hasData() && hasErrors()) {
            throw new VzaarException("The response data is not available as there was an error.");
        }

        return data;
    }

    public boolean hasData() {
        return data != null;
    }

    public boolean hasErrors() {
        return errors != null;
    }

    public List<VzaarApiError> getErrors() {
        return errors;
    }

    public String getErrorsAsString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (VzaarApiError error : getErrors()) {
            stringBuilder.append(error.toString()).append("\n");
        }
        return stringBuilder.toString();
    }
}
