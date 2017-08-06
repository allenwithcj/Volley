package com.wy.lwl.volley.http;

import com.alibaba.fastjson.JSON;

import java.io.UnsupportedEncodingException;

/**
 * Created by lwl on 2017/8/3.
 */

public class HttpTask implements Runnable{
    HttpService httpService;
    HttpListener httpListener;

    protected <T>HttpTask(T requestInfo, String url,HttpListener httpListener, HttpService httpService){
        this.httpListener = httpListener;
        this.httpService = httpService;
        //设置关系
        httpService.setHttpCallBack(httpListener);
        httpService.setUrl(url);
        //设置请求参数 json
        if(requestInfo != null){
            String jsonContent = JSON.toJSONString(requestInfo);
            try {
                this.httpService.setRequest(jsonContent.getBytes("utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
    //执行网络请求
        httpService.excute();
    }
}
