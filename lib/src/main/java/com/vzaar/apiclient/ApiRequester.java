package com.vzaar.apiclient;

import com.vzaar.apiclient.request.VzaarRequest;
import com.vzaar.apiclient.response.VzaarResponse;

import java.lang.reflect.Type;

interface ApiRequester {

    <T extends VzaarResponse> void sendRequest(VzaarRequest request, Type responseType, VzaarCallback<T> callback);
    <T extends VzaarResponse> void sendImageFrameRequest(VzaarRequest request, Type responseType, VzaarCallback<T> callback);
    <T extends VzaarResponse> void sendFileRequest(VzaarRequest request, Type responseType, VzaarCallback<T> callback);

    void setAuthentication(String clientId, String token);
}
