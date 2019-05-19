package com.iahsnil.nine.util;

import okhttp3.*;
import okhttp3.OkHttpClient;

/**
 * @Author: zed
 * @Date: 2019/5/7 10:38
 * @Description: okhttp工具类
 */
public class OkHttpUtil {

    private final static OkHttpClient client = com.iahsnil.nine.util.OkHttpClient.CLIENT.getClientInstance();

    public static Object post(String url, String data) throws Exception {
        Request request = new Request.Builder().url(url).post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), data)).build();
        Response response = client.newCall(request).execute();
        if (response.code() == 200) {
            return request.body();
        }
        return null;
    }

}
