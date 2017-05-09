package com.itkimi.network.utils;

import com.itkimi.network.RequestParams;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Headers;

/**
 * Created by kimi on 2017/5/5 0005.
 * Email: 24750@163.com
 */

public class OkhttpUtils {

    public static String getUrlWithParams(RequestParams requestParams) {
        String url = requestParams.url;
        StringBuffer sb = new StringBuffer();
        HashMap<String, String> params = requestParams.requestParams;
        if (params == null) return url;

        Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> next = iterator.next();
            String key = next.getKey();
            String value = next.getValue();
            sb.append(key + "=").append(value + "&");
        }

        return url.endsWith("?") ? url + sb.toString() : url + "?" + sb.toString();
    }

    public static FormBody getPostBody(RequestParams requestParams) {
        FormBody.Builder bodyBuilder = new FormBody.Builder();
        HashMap<String, String> params = requestParams.requestParams;
        if (params == null) return bodyBuilder.build();

        Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> next = iterator.next();
            String key = next.getKey();
            String value = next.getValue();
            bodyBuilder.addEncoded(key, value);
        }
        return bodyBuilder.build();
    }

    public static Headers getHeaders(RequestParams requestParams) {
        Headers.Builder builder = new Headers.Builder();
        HashMap<String, String> params = requestParams.header;
        if (params == null) return builder.build();

        Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> next = iterator.next();
            builder.add(next.getKey(), next.getValue());
        }
        return builder.build();
    }
}
