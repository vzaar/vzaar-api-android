package com.vzaar.apiclient.sample.app;

import android.app.Application;

import com.vzaar.apiclient.Vzaar;
import com.vzaar.apiclient.VzaarConfig;
import com.vzaar.apiclient.sample.BuildConfig;

import timber.log.Timber;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        String authClientId = "your-client-id";
        String authToken = "yourtoken";

        Vzaar.newInstance(
                new VzaarConfig.Builder(authClientId, authToken)
                        .setMaxSinglePartFileBytes(5 * 1024 * 1024) // 5 megabytes
                        .build());
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

    }
}
