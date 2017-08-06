package com.wy.lwl.volley.http;

import com.wy.lwl.volley.ThreadPoolManager;

/**
 * Created by lwl on 2017/8/3.
 * 调用层使用
 */

public class Volley {
    /**
     *
     * @param requestInfo
     * @param url
     * @param response
     * @param dateListener
     * @param <T>
     * @param <M>
     */
    public static <T,M>void sendJSONREquest(T requestInfo,String url,
                                             Class<M> response,DateListener dateListener){
        //策略模式
        HttpService httpService = new JsonHttpService();
        HttpListener httpListener = new JsonHttpListener<>(dateListener,response);
        HttpTask httpTask = new HttpTask(requestInfo,url,httpListener,httpService);

        ThreadPoolManager.getInstance().execute(httpTask);
    }
}
