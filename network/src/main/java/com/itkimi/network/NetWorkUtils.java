package com.itkimi.network;

import com.itkimi.network.call.ICallBack;
import com.itkimi.network.executor.IExecutor;
import com.itkimi.network.executor.factory.ExecutorFactory;

/**
 * Created by kimi on 2017/5/5 0005.
 * Email: 24750@163.com
 */

public final class NetWorkUtils {

    public static IExecutor executor;
    public static NetWorkUtils mInstance;

    private NetWorkUtils(ExecutorFactory factory) {
        executor = factory.create();
    }

    /**
     * init networkutils
     * 设置底层使用哪种网络实现，目前只实现了OkHttpExecutorFactory，如果想扩展的话请继承ExecutorFactory自行实现
     * @param factory
     */
    public static void init(ExecutorFactory factory) {
        if (mInstance == null) {
            synchronized (NetWorkUtils.class) {
                if (mInstance == null) {
                    mInstance = new NetWorkUtils(factory);
                }
            }
        }
    }

    public static NetWorkUtils getInstance() {
        if (mInstance == null || executor == null) {
            throw new RuntimeException("on load application not NetWorkUtils.init()");
        }
        return mInstance;
    }

    /**
     * start request
     * 开始网络请求，根据不同的请求方法选择相应的具体实现
     * @param params
     * @param callBack
     */
    public void doStart(RequestParams params, ICallBack callBack) {
        if (params == null || callBack == null) return;
        params.callback = callBack;
        switch (params.method) {
            case GET:
                executor.doGet(params, callBack);
                break;
            case POST:
                executor.doPost(params, callBack);
                break;
            case POST_JSON:
                executor.doPostJson(params, callBack);
                break;
            case UPLOAD:
                executor.doUploadFile(params, callBack);
                break;
            case DOWNLOAD:
                executor.doDownLoad(params, callBack);
                break;
        }
    }

    /**
     * 取消某个请求,一般在页面销毁的时候取消
     * @param tag
     */
    public void cancleRequest(Object tag) {
        executor.cancel(tag);
    }

    /**
     * 取消所有的请求
     */
    public void cancleAllRequest() {
        executor.cancelAll();
    }
}
