package com.itkimi.network.call;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.itkimi.network.Method;
import com.itkimi.network.NetWorkUtils;
import com.itkimi.network.RequestParams;
import com.itkimi.network.help.TempleteBean;
import com.itkimi.network.handler.MainHandler;
import com.itkimi.network.utils.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by kimi on 2017/5/5 0005.
 * Email: 24750@163.com
 */

public class OkhttpCallback implements Callback {

    public static final Handler handler = new MainHandler(Looper.getMainLooper());

    RequestParams requestParams;

    public OkhttpCallback(RequestParams requestParams) {
        this.requestParams = requestParams;
    }

    @Override
    public void onFailure(Call call, IOException e) {
        e.printStackTrace();
        TempleteBean templeteBean = new TempleteBean();
        templeteBean.call = call;
        templeteBean.e = e;
        templeteBean.requestParams = requestParams;
        Message.obtain(handler, MainHandler.FAILD, templeteBean).sendToTarget();
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        TempleteBean templeteBean = new TempleteBean();
        templeteBean.call = call;
        templeteBean.response = response;
        templeteBean.requestParams = requestParams;

        //downloadfile
        if (requestParams.method.equals(Method.DOWNLOAD)) {
            downLoadFile(templeteBean, response);
        }

        Message.obtain(handler, MainHandler.SUCCESS, templeteBean).sendToTarget();
    }

    private void downLoadFile(TempleteBean templeteBean, Response response) {
        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        try {
            fileOutputStream = new FileOutputStream(new File(requestParams.downLoadFilePath));
            long totalSize = response.body().contentLength();
            long temp = 0;
            inputStream = response.body().byteStream();
            byte[] bys = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(bys)) != -1) {
                temp += len;
                float progress = temp * 1.0f / totalSize * 100;
                templeteBean.progress = progress;
                Message.obtain(handler, MainHandler.PROGRESS, templeteBean).sendToTarget();
                fileOutputStream.write(bys, 0, len);
                fileOutputStream.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            FileUtils.closeIO(fileOutputStream, inputStream);
        }
    }
}
