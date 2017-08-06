package com.wy.lwl.volley.http;

/**
 * Created by lwl on 2017/8/3.
 * 执行接口
 * 设置url
 * 设置请求参数
 * 执行请求
 */

public interface HttpService {
    void setUrl(String url);

    void excute();

    void setRequest(byte[] requestDate);

    void setHttpCallBack(HttpListener httpListener);


}
