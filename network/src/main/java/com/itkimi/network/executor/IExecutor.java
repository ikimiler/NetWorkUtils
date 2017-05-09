package com.itkimi.network.executor;

import com.itkimi.network.RequestParams;
import com.itkimi.network.call.ICallBack;

/**
 * Created by kimi on 2017/5/5 0005.
 * Email: 24750@163.com
 */

public interface IExecutor {

    /**
     * get请求
     */
    void doGet(RequestParams requestParams, ICallBack callback);

    /**
     * post请求 key-value
     */
    void doPost(RequestParams requestParams,ICallBack callback);

    /**
     * post请求 json
     */
    void doPostJson(RequestParams requestParams,ICallBack callback);

    /**
     * uploadFile
     */
    void doUploadFile(RequestParams requestParams,ICallBack callback);

    /**
     * downLoad
     */
    void doDownLoad(RequestParams requestParams,ICallBack callback);

    /**
     * 取消请求
     */
    void cancel(Object tag);

    /**
     * 取消所有请求
     */
    void cancelAll();
}
