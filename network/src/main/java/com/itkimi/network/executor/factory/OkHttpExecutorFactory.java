package com.itkimi.network.executor.factory;

import android.util.Log;

import com.itkimi.network.NetWorkUtils;
import com.itkimi.network.config.OkhttpConfig;
import com.itkimi.network.executor.IExecutor;
import com.itkimi.network.executor.OkHttpExecutor;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by kimi on 2017/5/5 0005.
 * Email: 24750@163.com
 */

public class OkHttpExecutorFactory extends ExecutorFactory {

    @Override
    public IExecutor create() {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d(NetWorkUtils.class.getSimpleName(), message);
            }
        });

        //建造者模式，生成配置对象
        OkhttpConfig config = new OkhttpConfig.Builder().retryOnConnectionFailure(false).connectTimeout(10000).readTimeout(5000).writeTimeout(5000).interceptors(httpLoggingInterceptor).build();

        return new OkHttpExecutor(config);
    }
}
