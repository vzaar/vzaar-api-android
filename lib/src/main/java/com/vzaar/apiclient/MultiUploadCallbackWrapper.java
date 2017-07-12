package com.vzaar.apiclient;

import java.io.IOException;
import java.util.concurrent.Executor;

import okhttp3.Call;
import okhttp3.Response;

class MultiUploadCallbackWrapper {
    private final ProgressListener progressListener;
    private final int totalParts;
    private final Executor callbackExecutor;
    private int successfulParts;
    private int failedParts;
    private boolean calledSuccessOrFailure;

    MultiUploadCallbackWrapper(int totalParts, ProgressListener progressListener, Executor callbackExecutor) {
        this.totalParts = totalParts;
        this.progressListener = progressListener;
        this.callbackExecutor = callbackExecutor;
    }

    void execute(Call call) {
        if (completedParts() == 0) {
            notifyProgressChange();
        }

        try {
            Response response = call.execute();
            onResponse(call, response);
            response.close();
        } catch (IOException e) {
            onFailure(call, e);
        }
    }

    private int completedParts() {
        return successfulParts + failedParts;
    }

    private void updateParts(Response response) {
        if (response.isSuccessful()) {
            successfulParts++;
        } else {
            failedParts++;
        }
    }

    @SuppressWarnings("UnusedParameters")
    private void onResponse(final Call call, final Response response) {
        System.out.println("---Part completed");

        updateParts(response);

        notifyProgressChange();

        // Do not continue unless we need to notify about a success or failure
        if (calledSuccessOrFailure ||
                completedParts() < totalParts) {
            return;
        }

        callbackExecutor.execute(new Runnable() {
            @Override
            public void run() {
                if (response.isSuccessful()) {
                    progressListener.onSuccess();
                } else {
                    progressListener.onFailure(new Exception(response.message()));
                }
            }
        });

        calledSuccessOrFailure = true;
    }

    @SuppressWarnings("UnusedParameters")
    private void onFailure(final Call call, final IOException e) {
        System.out.println("---Part failed");
        notifyProgressChange();
        callbackExecutor.execute(new Runnable() {
            @Override
            public void run() {
                progressListener.onFailure(e);
            }
        });
        calledSuccessOrFailure = true;
        failedParts++;
    }

    private void notifyProgressChange() {
        if (progressListener != null) {
            callbackExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    progressListener.onChange(successfulParts, failedParts, totalParts);
                }
            });
        }
    }

    interface ProgressListener {
        void onSuccess();

        void onFailure(Exception e);

        void onChange(int successfulParts, int failedParts, int totalParts);
    }
}
