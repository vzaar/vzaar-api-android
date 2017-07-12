package com.vzaar.apiclient;

import com.vzaar.apiclient.request.CreateMultipartUploadSignatureRequest;
import com.vzaar.apiclient.request.CreateSingleUploadSignatureRequest;

/**
 * Holds general configuration details e.g. authentication keys.
 */
public class VzaarConfig {
    private static final String API_BASE_URL = "https://api.vzaar.com/";
    private final String authClientId;
    private final String authToken;
    private final long maxSinglePartFileBytes;

    private VzaarConfig(String authClientId, String authToken, long maxSinglePartFileBytes) {
        this.authClientId = authClientId;
        this.authToken = authToken;
        this.maxSinglePartFileBytes = maxSinglePartFileBytes;
    }

    String getApiBaseUrl() {
        return API_BASE_URL;
    }

    public String getAuthClientId() {
        return authClientId;
    }

    public String getAuthToken() {
        return authToken;
    }

    public long getMaxSinglePartFileBytes() {
        return maxSinglePartFileBytes;
    }

    public static class Builder {
        private final String authClientId;
        private final String authToken;
        private long maxSinglePartFileBytes = 16 * 1024 * 1024; // 16 megabytes

        /**
         * @param authClientId Vzaar client id
         * @param authToken    Vzaar token
         */
        public Builder(String authClientId, String authToken) {
            this.authClientId = authClientId;
            this.authToken = authToken;
        }

        /**
         * Set the maximum file size to be uploaded via a single-part upload. This option is only
         * when automatically selecting which type of signature to create. The option is overridden
         * by calling one of the following for signature creation:
         *
         * <ul>
         *     <li>{@link Vzaar#createSingleUploadSignature(CreateSingleUploadSignatureRequest, VzaarCallback)}</li>
         *     <li>{@link Vzaar#createMultipartUploadSignature(CreateMultipartUploadSignatureRequest, VzaarCallback)}</li>
         * </ul>
         * @param maxSinglePartFileBytes The max number of bytes before a multi-part upload
         *                               is automatically preferred.
         */
        public Builder setMaxSinglePartFileBytes(long maxSinglePartFileBytes) {
            this.maxSinglePartFileBytes = maxSinglePartFileBytes;
            return this;
        }

        public VzaarConfig build() {
            return new VzaarConfig(authClientId, authToken, maxSinglePartFileBytes);
        }
    }
}
