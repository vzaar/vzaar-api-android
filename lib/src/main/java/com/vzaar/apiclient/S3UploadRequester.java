package com.vzaar.apiclient;

import com.vzaar.apiclient.model.UploadSignature;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicBoolean;

import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;

class S3UploadRequester implements UploadRequester {

    private final Platform platform;
    private final OkHttpClient httpClient;

    S3UploadRequester(Platform platform, OkHttpClient httpClient) {
        this.platform = platform;
        this.httpClient = httpClient;
    }

    private static RequestBody createRequestBody(ByteString fileData, String filename,
                                                 String mimeType,
                                                 UploadSignature uploadSignature, int partIndex) {
        MultipartBody.Builder bodyBuilder = new MultipartBody.Builder()
                .addFormDataPart("key", uploadSignature.getKeyForUpload(filename, partIndex))
                .addFormDataPart("AWSAccessKeyId", uploadSignature.getAccessKeyId())
                .addFormDataPart("acl", uploadSignature.getAcl())
                .addFormDataPart("policy", uploadSignature.getPolicy())
                .addFormDataPart("signature", uploadSignature.getSignature())
                .addFormDataPart("success_action_status",
                        uploadSignature.getSuccessActionStatus())
                .addFormDataPart("x-amz-meta-uploader", "android-java")
                .addFormDataPart("file", filename,
                        RequestBody.create(MediaType.parse(mimeType), fileData));

        return bodyBuilder.build();

    }

    private static ByteString readChunk(BufferedSource source, int maxBytesToRead) throws IOException {
        ByteString chunk;
        try {
            // Try to read the max number of bytes
            chunk = source.readByteString(maxBytesToRead);
        } catch (IOException e) {
            // Fall back to reading the remainder of bytes
            chunk = source.readByteString();
        }
        return chunk;
    }

    private static Headers createHeaders(UploadSignature uploadSignature) {
        return new Headers.Builder()
                .add("x-amazon-acl", uploadSignature.getAcl())
                .add("Enclosure-Type", "multipart/form-data")
                .build();
    }

    @Override
    public VzaarCall uploadVideo(final VzaarUploadRequest uploadRequest,
                                 final UploadSignature uploadSignature,
                                 final UploadRequester.Callback uploadCallback) {
        final SourceVideo sourceVideo = uploadRequest.getSourceVideo();

        final int totalParts = numPartsToUpload(uploadSignature);
        final int fileBytesPerUpload = bytesPerUpload(uploadSignature,
                uploadRequest.getSourceVideo().getFileSizeBytes());

        final MultiUploadCallbackWrapper.ProgressListener progressListener =
                createProgressListener(uploadCallback);
        final MultiUploadCallbackWrapper batcher = new MultiUploadCallbackWrapper(
                totalParts, progressListener, platform.mainExecutor());

        final HttpUrl url = HttpUrl.parse(uploadSignature.getUploadHostname());

        final AtomicBoolean running = new AtomicBoolean(true);
        platform.backgroundExecutor().execute(new Runnable() {
            @Override
            public void run() {
                try (InputStream in = sourceVideo.openInputStream()) {
                    final BufferedSource source = Okio.buffer(Okio.source(in));
                    for (int curPart = 0; !source.exhausted() && running.get(); curPart++) {
                        ByteString fileData = readChunk(source, fileBytesPerUpload);
                        okhttp3.Request request =
                                createRequest(fileData, sourceVideo.getFilename(),
                                        sourceVideo.getMimeType(), uploadSignature, url, curPart);
                        batcher.execute(httpClient.newCall(request));
                    }
                } catch (IOException e) {
                    uploadCallback.onErrorResponse(e);
                }
            }
        });

        return new VzaarCall() {
            @Override
            public void cancel() {
                running.set(false);
            }
        };
    }

    private int bytesPerUpload(UploadSignature signature, long filesize) {
        long bytes = signature.isMultipart() ?
                signature.getPartSizeInBytes() : filesize;
        return (int) Math.min((long) Integer.MAX_VALUE, bytes);
    }

    private int numPartsToUpload(UploadSignature signature) {
        return signature.isMultipart() ? signature.getParts() : 1;
    }

    private Request createRequest(ByteString fileData, String filename, String mimeType,
                                  UploadSignature uploadSignature, HttpUrl url, int partNum) {
        return new okhttp3.Request.Builder()
                .url(url)
                .headers(createHeaders(uploadSignature))
                .post(createRequestBody(fileData, filename, mimeType,
                        uploadSignature,
                        partNum))
                .build();
    }

    private MultiUploadCallbackWrapper.ProgressListener createProgressListener(final UploadRequester.Callback callback) {
        return new MultiUploadCallbackWrapper.ProgressListener() {
            @Override
            public void onSuccess() {
                callback.onSuccessResponse();
            }

            @Override
            public void onFailure(Exception e) {
                callback.onErrorResponse(e);
            }

            @Override
            public void onChange(int successfulParts, int failedParts, int totalParts) {
                callback.onProgress(successfulParts, failedParts, totalParts);
            }
        };
    }
}
