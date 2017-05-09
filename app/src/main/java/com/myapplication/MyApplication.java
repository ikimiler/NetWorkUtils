package com.myapplication;

import android.app.Application;

import com.itkimi.network.NetWorkUtils;
import com.itkimi.network.executor.factory.OkHttpExecutorFactory;

/**
 * Created by kimi on 2017/5/5 0005.
 * Email: 24750@163.com
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        NetWorkUtils.init(new OkHttpExecutorFactory());
    }
}
