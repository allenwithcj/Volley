package com.wy.lwl.volley.http;

/**
 * Created by lwl on 2017/8/3.
 * 结果回调接口
 */

public interface DateListener<M> {
    //回调结果使用泛型
    void onSuccess(M m);
    void onFail();
}
