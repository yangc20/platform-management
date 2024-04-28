package com.mysystem.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mysystem.interceptor.CookieInterceptor;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class OkHttpUtils {

    public static OkHttpClient client = new OkHttpClient().newBuilder()
            .connectTimeout(3000, TimeUnit.MILLISECONDS)
            .addInterceptor(new CookieInterceptor())
            .build();

    private static final Gson gson = new Gson();

    public static Object doGet(String url, Map<String, String> headerMap, TypeToken typeToken) {
        headerMap.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.71 Safari/537.36 SE 2.X MetaSr 1.0");
        headerMap.put("Connection", "keep-alive");
        Request request = new Request.Builder()
                .headers(Headers.of(headerMap))
                .url(url)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String responseData = response.body().string();
                // 使用Gson解析JSON响应为对象
                return gson.fromJson(responseData, typeToken.getType());
            } else {
                throw new RuntimeException("请求失败: " + response.code());
            }
        } catch (IOException e) {
            throw new RuntimeException("请求异常", e);
        }

    }

    public static Object doPost(String url, Map<String, String> headerMap, String bodyStr, TypeToken typeToken) {
        headerMap.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.71 Safari/537.36 SE 2.X MetaSr 1.0");
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), bodyStr);
        Request request = new Request.Builder()
                .url(url)
                .headers(Headers.of(headerMap))
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String responseData = response.body().string();
                // 使用Gson解析JSON响应为对象
                return gson.fromJson(responseData, typeToken.getType());
            } else {
                throw new RuntimeException("请求失败: " + response.code());
            }
        } catch (IOException e) {
            throw new RuntimeException("请求异常", e);
        }

    }
}
