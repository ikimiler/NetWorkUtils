package com.itkimi.network.help;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.itkimi.network.RequestParams;
import com.itkimi.network.handler.MainHandler;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

/**
 * Created by kimi on 2017/5/8 0008.
 * Email: 24750@163.com
 */

public class ProgressRequestBody extends RequestBody {

    private final RequestBody body;
    private final RequestParams params;
    private BufferedSink bufferedSink;
    private TempleteBean templeteBean = new TempleteBean();
    private static final Handler handler = new MainHandler(Looper.getMainLooper());

    public ProgressRequestBody(RequestParams params,RequestBody body){
        this.body = body;
        this.params = params;
        templeteBean.requestParams = this.params;
    }

    @Override
    public MediaType contentType() {
        return body.contentType();
    }

    @Override
    public long contentLength() throws IOException {
        return body.contentLength();
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        if (bufferedSink == null) {
            bufferedSink = Okio.buffer(sink(sink));
        }
        body.writeTo(bufferedSink);
        //必须调用flush，否则最后一部分数据可能不会被写入
        bufferedSink.flush();

    }

    private Sink sink(Sink sink) {
        return new ForwardingSink(sink) {
            private long current;
            private long total;

            @Override
            public void write(Buffer source, long byteCount) throws IOException {
                super.write(source, byteCount);
                if (total == 0) {
                    total = contentLength();
                }
                current += byteCount;
                float progress = current * 1.0f / total * 100;
                templeteBean.progress = progress;
                Message.obtain(handler,MainHandler.PROGRESS,templeteBean).sendToTarget();
            }
        };
    }

}
