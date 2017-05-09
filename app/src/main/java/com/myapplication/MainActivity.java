package com.myapplication;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.itkimi.network.Method;
import com.itkimi.network.NetWorkUtils;
import com.itkimi.network.RequestParams;
import com.itkimi.network.call.ICallBack;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button get, post, postJson, upload, download;
    NumberProgressBar uploadProgress,downloadProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        get = (Button) findViewById(R.id.get);
        post = (Button) findViewById(R.id.post);
        postJson = (Button) findViewById(R.id.postJson);
        upload = (Button) findViewById(R.id.upload);
        download = (Button) findViewById(R.id.download);

        uploadProgress = (NumberProgressBar) findViewById(R.id.uploadProgress);
        downloadProgress = (NumberProgressBar) findViewById(R.id.downloadProgress);

        get.setOnClickListener(this);
        post.setOnClickListener(this);
        postJson.setOnClickListener(this);
        upload.setOnClickListener(this);
        download.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.get:
                doGet();
                break;
            case R.id.post:
                doPost();
                break;
            case R.id.postJson:
                doPostJson();
                break;
            case R.id.upload:
                doUploadFile();
                break;
            case R.id.download:
                doDownload();
                break;
        }
    }

    private void doGet() {
        String url = "http://127.0.0.1:8080/test/test";
        RequestParams params = new RequestParams.Builder().url(url).method(Method.GET).params("key1", "hello").params("key2", "doGet").tag(this).build();
        NetWorkUtils.getInstance().doStart(params, callback);
    }

    private void doPost() {
        String url = "http://127.0.0.1:8080/test/test";
        RequestParams params = new RequestParams.Builder().url(url).method(Method.POST).params("key1", "hello").params("key2", "doPost").tag(this).build();
        NetWorkUtils.getInstance().doStart(params, callback);
    }

    private void doPostJson() {
        String url = "http://127.0.0.1:8080/test/test";
        String body = "{ \"key1\":\"hello\",\"key2\":\"doPostJson\" }";
        RequestParams params = new RequestParams.Builder().url(url).method(Method.POST_JSON).body(body).tag(this).build();
        NetWorkUtils.getInstance().doStart(params, callback);
    }

    private void doUploadFile() {
        String url = "http://127.0.0.1:8080/test/upload";
        String filePath = Environment.getExternalStorageDirectory().getPath() + File.separator + "atlas-master.zip";
        RequestParams params = new RequestParams.Builder().url(url).method(Method.UPLOAD).files("fileKey", new File(filePath)).tag(this).build();
        NetWorkUtils.getInstance().doStart(params, new ICallBack(){
            @Override
            public void onStart() {
                Log.d(NetWorkUtils.class.getSimpleName(), "网络开始加载");
            }

            @Override
            public void onComplite() {
                Log.d(NetWorkUtils.class.getSimpleName(), "网络加载完成");
                uploadProgress.setProgress(0);
            }

            @Override
            public void onError(Exception e) {

            }

            @Override
            public void onSuccess(Object tag, String result) {
                new AlertDialog.Builder(MainActivity.this).setMessage(result).create().show();
            }

            @Override
            public void onProgress(float progress) {
                uploadProgress.setProgress((int) progress);
            }
        });
    }

    private void doDownload() {
        String url = "http://127.0.0.1:8080/test/download";
        String filePath = Environment.getExternalStorageDirectory().getPath() + File.separator + "atlas-master.zip";
        RequestParams params = new RequestParams.Builder().url(url).method(Method.DOWNLOAD).downLoadFilePath(filePath).tag(this).build();
        NetWorkUtils.getInstance().doStart(params, new ICallBack(){
            @Override
            public void onStart() {

                Log.d(NetWorkUtils.class.getSimpleName(), "网络开始加载");
            }

            @Override
            public void onComplite() {
                Log.d(NetWorkUtils.class.getSimpleName(), "网络加载完成");
                downloadProgress.setProgress(0);
            }

            @Override
            public void onError(Exception e) {

            }

            @Override
            public void onSuccess(Object tag, String result) {
                new AlertDialog.Builder(MainActivity.this).setMessage(result).create().show();
            }

            @Override
            public void onProgress(float progress) {
                downloadProgress.setProgress((int) progress);
            }
        });
    }

    private ICallBack callback = new ICallBack() {

        @Override
        public void onStart() {
            Log.d(NetWorkUtils.class.getSimpleName(), "网络开始加载");
        }

        @Override
        public void onComplite() {
            Log.d(NetWorkUtils.class.getSimpleName(), "网络加载完成");
        }

        @Override
        public void onError(Exception e) {
            Log.d(NetWorkUtils.class.getSimpleName(), "出现错误");
        }


        @Override
        public void onSuccess(Object tag, String result) {

            /**
             * 可以根据不同的tag来解析数据
             * if(tag.equest("doGet")){
             *
             * }else if(tag.equest("doPost")){
             *
             * }
             * ............
             */
            new AlertDialog.Builder(MainActivity.this).setMessage(result).create().show();
        }

        @Override
        public void onProgress(float progress) {
            Log.d(NetWorkUtils.class.getSimpleName(), "上传或下载进度 : "+progress);
        }
    };


    /**
     * 取消网络请求
     */
    @Override
    protected void onDestroy() {
        NetWorkUtils.getInstance().cancleRequest(this);
        super.onDestroy();
    }
}
