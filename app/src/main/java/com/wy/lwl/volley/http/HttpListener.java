package com.wy.lwl.volley.http;

import java.io.InputStream;

/**
 * Created by lwl on 2017/8/3.
 * 处理接口
 */

public interface HttpListener {
    //接收上一个接口的结果
    void onSuccess(InputStream inputStream);

    void onFail();
}
