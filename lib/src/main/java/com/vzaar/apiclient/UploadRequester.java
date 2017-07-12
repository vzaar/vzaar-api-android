package com.vzaar.apiclient;

import com.vzaar.apiclient.model.UploadSignature;

interface UploadRequester {

    VzaarCall uploadVideo(VzaarUploadRequest request,
                          UploadSignature uploadSignature,
                          final Callback callback);

    interface Callback {
        void onSuccessResponse();

        void onProgress(long successfulParts, long failedParts, long totalBytes);

        void onErrorResponse(Exception e);

    }
}
