package com.vzaar.apiclient;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vzaar.apiclient.request.VzaarRequest;
import com.vzaar.apiclient.response.VzaarResponse;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;

class OkHttpApiRequester implements ApiRequester {
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private final OkHttpClient okHttpClient;
    private final Platform platform;
    private final Gson gson;
    private final String baseUrl;
    private String clientId;
    private String token;

    OkHttpApiRequester(String baseUrl, Platform platform, OkHttpClient okHttpClient) {
        this.baseUrl = baseUrl;
        this.okHttpClient = okHttpClient;
        this.gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        this.platform = platform;
    }

    @Override
    public void setAuthentication(String clientId, String token) {
        this.clientId = clientId;
        this.token = token;
    }

    @Override
    public <T extends VzaarResponse> void sendRequest(VzaarRequest request, Type responseType, VzaarCallback<T> callback) {
        HttpUrl url = HttpUrl.get(request.buildUrl(baseUrl));

        okhttp3.Request.Builder requestBuilder = new okhttp3.Request.Builder()
                .url(url)
                .addHeader("X-Client-Id", clientId)
                .addHeader("X-Auth-Token", token);


        if (request.getMethod() != VzaarRequest.Method.GET) {
            RequestBody body = RequestBody.create(JSON, request.getBodyAsJson());
            switch (request.getMethod()) {
                case POST:
                    requestBuilder = requestBuilder.post(body);
                    break;
                case PATCH:
                    requestBuilder = requestBuilder.patch(body);
                    break;
                case DELETE:
                    requestBuilder = requestBuilder.delete(body);
                    break;
            }
        }

        Callback<T> handler = new Callback<>(gson, callback, responseType, platform);

        Call call = okHttpClient.newCall(requestBuilder.build());

        call.enqueue(handler);
    }

    private static class Callback<T extends VzaarResponse> implements okhttp3.Callback {
        private final Gson gson;
        private final VzaarCallback<T> vzaarCallback;
        private final Type responseType;
        private final Platform platform;

        Callback(Gson gson, VzaarCallback<T> vzaarCallback, Type responseType, Platform platform) {
            this.gson = gson;
            this.vzaarCallback = vzaarCallback;
            this.responseType = responseType;
            this.platform = platform;
        }

        @Override
        public final void onResponse(final Call call, final Response response) throws IOException {
            final T obj = parseResponse(response);

            platform.mainExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    if (obj.hasData()) {
                        vzaarCallback.onSuccessResponse(obj);
                    } else if (obj.hasErrors()) {
                        vzaarCallback.onErrorResponse(obj);
                    } else if (response.code() == 500) {
                        vzaarCallback.onNoResponse(
                                new VzaarException("There was an internal server error (500)."));
                    } else {
                        vzaarCallback.onNoResponse(
                                new VzaarException("The server did not return any data or error."));
                    }
                }
            });
        }

        @Override
        public final void onFailure(final Call call, final IOException e) {
            platform.mainExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    vzaarCallback
                            .onNoResponse(new VzaarException("There was a connection problem.", e));
                }
            });
        }

        private T parseResponse(final Response response) throws IOException {
            String json;
            // If we get an HTTP no content (204) response, substitute an empty data json so GSON
            // can parse to our response type.
            if (response.code() == 204) {
                json = "{data:{}}";
            } else {
                json = response.body().string();
            }
            return gson.fromJson(json, responseType);
        }
    }
}
