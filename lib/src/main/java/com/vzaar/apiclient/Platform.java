package com.vzaar.apiclient;

import java.util.concurrent.Executor;

interface Platform {
    Executor mainExecutor();

    Executor backgroundExecutor();
}
