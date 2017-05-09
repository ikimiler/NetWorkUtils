package com.itkimi.network;

import com.itkimi.network.call.ICallBack;

import java.io.File;
import java.nio.charset.Charset;
import java.util.HashMap;


/**
 * Created by kimi on 2017/5/5 0005.
 * Email: 24750@163.com
 */

public final class RequestParams {

    public String url;
    public HashMap<String, String> header = new HashMap<>();
    public HashMap<String, String> requestParams = new HashMap<>();
    public HashMap<String, File> files = new HashMap<>();
    public Charset charset = Charset.forName("UTF-8");
    public Object tag;
    public Method method = Method.GET;
    public String body;
    public String downLoadFilePath;
    public ICallBack callback;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HashMap<String, String> getHeader() {
        return header;
    }

    public void setHeader(HashMap<String, String> header) {
        this.header = header;
    }

    public HashMap<String, String> getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(HashMap<String, String> requestParams) {
        this.requestParams = requestParams;
    }

    public HashMap<String, File> getFiles() {
        return files;
    }

    public void setFiles(HashMap<String, File> files) {
        this.files = files;
    }

    public Charset getCharset() {
        return charset;
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDownLoadFilePath() {
        return downLoadFilePath;
    }

    public void setDownLoadFilePath(String downLoadFilePath) {
        this.downLoadFilePath = downLoadFilePath;
    }

    public ICallBack getCallback() {
        return callback;
    }

    public void setCallback(ICallBack callback) {
        this.callback = callback;
    }

    public static class Builder {
        private String url;
        private HashMap<String, String> header = new HashMap<>();
        private HashMap<String, String> requestParams = new HashMap<>();
        private HashMap<String, File> files = new HashMap<>();
        private Charset charset = Charset.forName("UTF-8");
        private Object tag;
        public String downLoadFilePath;
        private Method method = Method.GET;
        private String body;

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder header(String key, String value) {
            this.header.put(key, value);
            return this;
        }

        public Builder params(String key, String value) {
            this.requestParams.put(key, value);
            return this;
        }

        public Builder files(String key, File file) {
            this.files.put(key, file);
            return this;
        }

        public Builder defaultCharset(Charset charset) {
            this.charset = charset;
            return this;
        }

        public Builder tag(Object tag) {
            this.tag = tag;
            return this;
        }

        public Builder method(Method method) {
            this.method = method;
            return this;
        }

        public Builder body(String body) {
            this.body = body;
            return this;
        }

        public Builder downLoadFilePath(String downLoadFilePath) {
            this.downLoadFilePath = downLoadFilePath;
            return this;
        }


        public RequestParams build() {
            RequestParams obj = new RequestParams();
            obj.url = this.url;
            obj.header = this.header;
            obj.requestParams = this.requestParams;
            obj.files = this.files;
            obj.charset = this.charset;
            obj.tag = this.tag;
            obj.method = this.method;
            obj.body = this.body;
            obj.downLoadFilePath = this.downLoadFilePath;
            return obj;
        }
    }
}
