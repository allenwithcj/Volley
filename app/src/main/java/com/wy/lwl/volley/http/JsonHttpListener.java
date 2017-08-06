package com.wy.lwl.volley.http;

import android.os.Handler;
import android.os.Looper;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by lwl on 2017/8/3.
 */

public class JsonHttpListener<M> implements HttpListener{
    private DateListener<M> dateListener;
    Class<M> responseClass;
    private Handler handler = new Handler(Looper.getMainLooper());

    public JsonHttpListener(DateListener<M> dateListener, Class<M> responseClass) {
        this.dateListener = dateListener;
        this.responseClass = responseClass;
    }

    //结果作为参数传进来 json inputsream-->string
    @Override
    public void onSuccess(InputStream inputStream) {
        String content = getContent(inputStream);
        //将内容解析成封装对象
        final M response = JSON.parseObject(content,responseClass);
        // 线程切换
        handler.post(new Runnable() {
            @Override
            public void run() {
                //回调的方法发生在主线程
                dateListener.onSuccess(response);
            }
        });
    }

    /**
     * Inputstream转string
     * @param inputStream
     * @return
     */
    private String getContent(InputStream inputStream) {
        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];
        try {
            for (int n; (n = inputStream.read(b)) != -1;) {
                out.append(new String(b, 0, n));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    @Override
    public void onFail() {

    }
}
