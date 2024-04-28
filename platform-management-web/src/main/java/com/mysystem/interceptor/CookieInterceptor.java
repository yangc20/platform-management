package com.mysystem.interceptor;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Headers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CookieInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());

        if (originalResponse.isRedirect()) {
            // 获取原始响应中的cookie
            List<String> headers = originalResponse.headers("Set-Cookie");
            // 创建新的请求，携带原始响应中的cookie
            Request.Builder builder = chain.request().newBuilder();
            if (CollectionUtils.isNotEmpty(headers)) {
                String s = headers.get(0);
                Map<String, String> headMap = new HashMap<>();
                headMap.put("Cookie", s);
                builder.headers(Headers.of(headMap));
            }

            // 使用新的请求重新执行拦截器链
            return chain.proceed(builder.build());
        }

        // 如果不是重定向响应，直接返回原始响应
        return originalResponse;
    }
}