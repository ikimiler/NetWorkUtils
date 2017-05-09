package com.itkimi.network.config;

import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cache;
import okhttp3.Interceptor;

/**
 * Created by kimi on 2017/5/5 0005.
 * Email: 24750@163.com
 */

public class OkhttpConfig {

    public boolean retryOnConnectionFailure;
    public long connectTimeout;
    public Cache cache;
    public long readTimeout;
    public long writeTimeout;
    public List<Interceptor> interceptors = new ArrayList<>();
    public List<Interceptor> networkInterceptors = new ArrayList<>();
    public SSLSocketFactory sslSocketFactory;
    public X509TrustManager x509TrustManager;

    public boolean isRetryOnConnectionFailure() {
        return retryOnConnectionFailure;
    }

    public void setRetryOnConnectionFailure(boolean retryOnConnectionFailure) {
        this.retryOnConnectionFailure = retryOnConnectionFailure;
    }

    public long getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(long connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public Cache getCache() {
        return cache;
    }

    public void setCache(Cache cache) {
        this.cache = cache;
    }

    public long getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(long readTimeout) {
        this.readTimeout = readTimeout;
    }

    public long getWriteTimeout() {
        return writeTimeout;
    }

    public void setWriteTimeout(long writeTimeout) {
        this.writeTimeout = writeTimeout;
    }

    public List<Interceptor> getInterceptors() {
        return interceptors;
    }

    public void setInterceptors(List<Interceptor> interceptors) {
        this.interceptors = interceptors;
    }

    public List<Interceptor> getNetworkInterceptors() {
        return networkInterceptors;
    }

    public void setNetworkInterceptors(List<Interceptor> networkInterceptors) {
        this.networkInterceptors = networkInterceptors;
    }

    public SSLSocketFactory getSslSocketFactory() {
        return sslSocketFactory;
    }

    public void setSslSocketFactory(SSLSocketFactory sslSocketFactory) {
        this.sslSocketFactory = sslSocketFactory;
    }

    public X509TrustManager getX509TrustManager() {
        return x509TrustManager;
    }

    public void setX509TrustManager(X509TrustManager x509TrustManager) {
        this.x509TrustManager = x509TrustManager;
    }

    public static class Builder {

        private boolean retryOnConnectionFailure;
        private long connectTimeout;
        private Cache cache;
        private long readTimeout;
        private long writeTimeout;
        private List<Interceptor> interceptors = new ArrayList<>();
        private List<Interceptor> networkInterceptors = new ArrayList<>();
        private SSLSocketFactory sslSocketFactory;
        private X509TrustManager x509TrustManager;

        public Builder retryOnConnectionFailure(boolean retryOnConnectionFailure) {
            this.retryOnConnectionFailure = retryOnConnectionFailure;
            return this;
        }

        public Builder connectTimeout(long connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;
        }

        public Builder cache(Cache cache) {
            this.cache = cache;
            return this;
        }

        public Builder readTimeout(long readTimeout) {
            this.readTimeout = readTimeout;
            return this;
        }

        public Builder writeTimeout(long writeTimeout) {
            this.writeTimeout = writeTimeout;
            return this;
        }

        public Builder interceptors(Interceptor interceptor) {
            this.interceptors.add(interceptor);
            return this;
        }

        public Builder networkInterceptors(Interceptor interceptor) {
            this.networkInterceptors.add(interceptor);
            return this;
        }

        public Builder sslSocketFactory(SSLSocketFactory sslSocketFactory) {
            this.sslSocketFactory = sslSocketFactory;
            return this;
        }

        public Builder x509TrustManager(X509TrustManager x509TrustManager) {
            this.x509TrustManager = x509TrustManager;
            return this;
        }

        public OkhttpConfig build() {
            OkhttpConfig okhttpConfig = new OkhttpConfig();
            okhttpConfig.cache = this.cache;
            okhttpConfig.connectTimeout = this.connectTimeout;
            okhttpConfig.interceptors = this.interceptors;
            okhttpConfig.networkInterceptors = this.networkInterceptors;
            okhttpConfig.readTimeout = this.readTimeout;
            okhttpConfig.writeTimeout = this.writeTimeout;
            okhttpConfig.retryOnConnectionFailure = this.retryOnConnectionFailure;
            okhttpConfig.sslSocketFactory = this.sslSocketFactory;
            okhttpConfig.x509TrustManager = this.x509TrustManager;
            return okhttpConfig;
        }

    }
}
