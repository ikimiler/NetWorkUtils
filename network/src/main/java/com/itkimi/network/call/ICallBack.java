package com.itkimi.network.call;

/**
 * Created by kimi on 2017/5/5 0005.
 * Email: 24750@163.com
 */

public abstract class ICallBack {

    public void onStart() {
    }

    public void onComplite() {
    }

    public abstract void onError(Exception e);

    public abstract void onSuccess(Object tag,String result);

    public void onProgress(float progress) {
    }

}
