package com.vzaar.apiclient.request;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.vzaar.apiclient.VzaarException;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import okhttp3.HttpUrl;

/**
 * A {@link VzaarRequest} represents a request to the Vzaar API. It should be passed
 * as an argument to a method in the {@link com.vzaar.apiclient.Vzaar} object.
 *
 * @see com.vzaar.apiclient.Vzaar
 */
public class VzaarRequest {

    private final String parameterizedEndpoint;
    private final Method method;
    private final JsonObject body;
    private final Map<String, String> queryParams;
    private final Map<String, String> pathParams;

    VzaarRequest(String parameterizedEndpoint, Method method) {
        this.parameterizedEndpoint = parameterizedEndpoint;
        this.method = method;
        queryParams = new HashMap<>();
        pathParams = new HashMap<>();
        body = new JsonObject();
    }

    Map<String, String> getQueryParams() {
        return queryParams;
    }

    Map<String, String> getPathParams() {
        return pathParams;
    }

    public Method getMethod() {
        return method;
    }

    JsonObject getBody() {
        return body;
    }

    public String getBodyAsJson() {
        return body.toString();
    }

    void addQueryParam(String key, String value) {
        queryParams.put(key, value);
    }

    void addPathParam(String key, String value) {
        pathParams.put(key, value);
    }

    void addBodyParam(String key, String value) {
        addBodyParam(key, new JsonPrimitive(value));
    }

    void addBodyParam(String key, Boolean value) {
        addBodyParam(key, new JsonPrimitive(value));
    }

    void addBodyParam(String key, Number value) {
        addBodyParam(key, new JsonPrimitive(value));
    }

    void addBodyParam(String key, JsonElement element) {
        body.add(key, element);
    }

    String getEndpoint() {
        String endPoint = parameterizedEndpoint;
        for (Map.Entry<String, String> pathParam : pathParams.entrySet()) {
            endPoint = endPoint.replace(
                    "{" + pathParam.getKey() + "}",
                    pathParam.getValue());
        }

        return endPoint;
    }

    public URL buildUrl(String baseUrl) throws VzaarException {
        HttpUrl.Builder urlBuilder = HttpUrl
                .parse(baseUrl + getEndpoint())
                .newBuilder();

        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            urlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
        }

        return urlBuilder.build().url();
    }

    public enum Method {
        GET, POST, PATCH, DELETE
    }

}
