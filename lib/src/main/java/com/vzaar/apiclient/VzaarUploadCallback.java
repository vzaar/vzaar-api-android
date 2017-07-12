package com.vzaar.apiclient;

import com.vzaar.apiclient.model.Video;
import com.vzaar.apiclient.response.VzaarResponse;

@SuppressWarnings("UnusedParameters")
public interface VzaarUploadCallback {

    void onComplete(VzaarResponse<Video> videoResponse);

    void onError(VzaarException vzaarException);

    void onProgress(long uploadedBytes, long failedParts, long totalBytes);
}
