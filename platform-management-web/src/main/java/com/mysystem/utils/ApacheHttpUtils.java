package com.mysystem.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mysystem.model.response.LotteryTicketDetailData;
import com.mysystem.model.response.TicketResponseData;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;

public class ApacheHttpUtils {

    private static final Gson gson = new Gson();


    private static final CloseableHttpClient httpClient = HttpClients.createDefault();

    public static Object doGet(String url, Map<String, String> headerMap, TypeToken typeToken) {

        HttpGet request = new HttpGet(url);

        // 设置自定义header
        headerMap.entrySet().forEach(entry -> {
            request.setHeader(entry.getKey(), entry.getValue());
        });

        // 设置共用header
        request.setHeader("Connection", "keep-alive");
        request.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.71 Safari/537.36 SE 2.X MetaSr 1.0");
//        request.setHeader("Cookie", "HMF_CI=09deeca4cb0aa0363c21b9c42aa03628d7469326556153c2e4c4607a90f9aa35d458917a8a2d202b608bf0de5501218f1d552d28ba0a5e7e62c714ff1af1660bb8; 21_vq=7");
        try (CloseableHttpResponse response = httpClient.execute(request)) {
//            System.out.println(response.getStatusLine());
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 读取响应内容
                String result = EntityUtils.toString(response.getEntity());
                System.out.println(result);
                return gson.fromJson(result, typeToken.getType());
            } else {
                System.out.println("请求失败，状态码：" + response.getStatusLine().getStatusCode());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
