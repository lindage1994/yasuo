package com.iahsnil.nine.util;

import java.util.concurrent.TimeUnit;

/**
 * @Author: zed
 * @Date: 2019/5/7 15:23
 * @Description: OkHttpClient
 */
public enum OkHttpClient {
    CLIENT;
    private okhttp3.OkHttpClient clientInstance;

    OkHttpClient() {
        clientInstance = new okhttp3.OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)
                .build();
    }
    public okhttp3.OkHttpClient getClientInstance() {
        return clientInstance;
    }
}
