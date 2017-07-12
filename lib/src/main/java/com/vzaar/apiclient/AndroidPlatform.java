package com.vzaar.apiclient;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;

class AndroidPlatform implements Platform {
    private static AndroidPlatform instance;
    private final Executor mainExecutor;
    private final Executor backgroundExecutor;

    private AndroidPlatform(Executor mainExecutor, Executor backgroundExecutor) {
        this.mainExecutor = mainExecutor;
        this.backgroundExecutor = backgroundExecutor;
    }

    static AndroidPlatform get() {
        if (instance == null) {
            instance = new AndroidPlatform(new MainThreadExecutor(),
                    new BackgroundThreadExecutor());
        }
        return instance;
    }

    @Override
    public Executor mainExecutor() {
        return mainExecutor;
    }

    @Override
    public Executor backgroundExecutor() {
        return backgroundExecutor;
    }

    private static class MainThreadExecutor implements Executor {
        private final Handler handler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable r) {
            handler.post(r);
        }
    }

    private static class BackgroundThreadExecutor implements Executor {

        @Override
        public void execute(@NonNull final Runnable r) {
            final HandlerThread handlerThread = new HandlerThread("VzaarBackgroundThread");
            handlerThread.start();
            final Handler handler = new Handler(handlerThread.getLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    r.run();
                    handlerThread.quit();
                }
            });
        }
    }
}

