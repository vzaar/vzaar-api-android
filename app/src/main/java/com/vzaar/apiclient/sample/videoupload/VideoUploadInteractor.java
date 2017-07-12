package com.vzaar.apiclient.sample.videoupload;

import android.content.ContentResolver;
import android.net.Uri;

import com.vzaar.apiclient.AndroidSourceVideo;
import com.vzaar.apiclient.SourceVideo;
import com.vzaar.apiclient.Vzaar;
import com.vzaar.apiclient.VzaarCall;
import com.vzaar.apiclient.VzaarException;
import com.vzaar.apiclient.VzaarUploadCallback;
import com.vzaar.apiclient.VzaarUploadRequest;
import com.vzaar.apiclient.model.Video;
import com.vzaar.apiclient.response.VzaarResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class VideoUploadInteractor {
    private final Vzaar vzaar;
    private final ContentResolver contentResolver;
    private final List<VzaarCall> storageCalls;

    VideoUploadInteractor(Vzaar vzaar, ContentResolver contentResolver) {
        this.vzaar = vzaar;
        this.contentResolver = contentResolver;
        storageCalls = new ArrayList<>();
    }

    void uploadVideo(Uri uri, String title, String description, final VideoUploadCompletedListener listener) {
        try {
            String filename = "sample_" + new Date().getTime();
            SourceVideo sourceVideo = new AndroidSourceVideo(contentResolver, uri, filename);
            VzaarUploadRequest request = new VzaarUploadRequest.Builder(sourceVideo)
                    .setVideoTitle(title)
                    .setVideoDescription(description)
                    .build();
            VzaarCall call = vzaar
                    .signUploadAndCreateVideo(request, createUploadCallback(listener));
            storageCalls.add(call);
            listener.onVideoUploadStarted();
        } catch (NullPointerException | IOException e) {
            listener.onVideoUploadFailed(e);
        }
    }

    private VzaarUploadCallback createUploadCallback(final VideoUploadCompletedListener listener) {
        return new VzaarUploadCallback() {
            @Override
            public void onComplete(VzaarResponse<Video> videoResponse) {
                listener.onVideoUploadCompleted(videoResponse.getData());
            }

            @Override
            public void onError(VzaarException vzaarException) {
                listener.onVideoUploadFailed(vzaarException);
            }

            @Override
            public void onProgress(long uploadedBytes, long failedParts, long totalBytes) {
                float percent = ((float) uploadedBytes / (float) totalBytes) * 100f;
                listener.onVideoUploadProgress(percent);
            }
        };
    }

    void cancelAll() {
        for (VzaarCall call : storageCalls) {
            call.cancel();
        }
        storageCalls.clear();
    }

    @SuppressWarnings("UnusedParameters")
    interface VideoUploadCompletedListener {
        void onVideoUploadStarted();

        void onVideoUploadCompleted(Video video);

        void onVideoUploadFailed(Throwable e);

        void onVideoUploadProgress(float percent);
    }

}
