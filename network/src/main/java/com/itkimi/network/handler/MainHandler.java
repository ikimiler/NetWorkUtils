package com.itkimi.network.handler;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.itkimi.network.Method;
import com.itkimi.network.NetWorkUtils;
import com.itkimi.network.RequestParams;
import com.itkimi.network.help.TempleteBean;
import com.itkimi.network.call.ICallBack;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by kimi on 2017/5/5 0005.
 * Email: 24750@163.com
 */

public class MainHandler extends Handler {

    public static final int SUCCESS = 101;
    public static final int FAILD = 102;
    public static final int PROGRESS = 103;

    public MainHandler(Looper looper) {
        super(looper);
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case SUCCESS:
                onSuccess(msg);
                break;
            case FAILD:
                onFaild(msg);
                break;
            case PROGRESS:
                onProgress(msg);
                break;
        }
    }

    private void onProgress(Message msg) {
        //download or uploadfile on progress
        TempleteBean templeteBean = (TempleteBean) msg.obj;
        templeteBean.requestParams.callback.onProgress(templeteBean.progress);
    }


    private void onSuccess(Message msg) {
        TempleteBean bean = (TempleteBean) msg.obj;
        RequestParams requestParams = bean.requestParams;
        ICallBack callback = requestParams.callback;
        Response response = bean.response;

        if (!response.isSuccessful()) {
            Log.d(NetWorkUtils.class.getSimpleName(), "!response.isSuccessful()" +response.code());
            return;
        }

        if (requestParams.method.equals(Method.DOWNLOAD)) {
            callback.onSuccess(requestParams.tag, requestParams.downLoadFilePath);
            callback.onComplite();
            return;
        }

        try {
            String result = response.body().string();
            Log.d(NetWorkUtils.class.getSimpleName(), result);
            callback.onSuccess(requestParams.tag, result);
        } catch (Exception e) {
            e.printStackTrace();
            callback.onError(e);
        }
        callback.onComplite();
    }

    private void onFaild(Message msg) {
        TempleteBean bean = (TempleteBean) msg.obj;
        RequestParams requestParams = bean.requestParams;
        IOException e = bean.e;
        ICallBack callback = requestParams.callback;
        callback.onError(e);
        callback.onComplite();
    }

}
