package com.wy.lwl.volley.http;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by lwl on 2017/8/3.
 */

public class JsonHttpService implements HttpService{
    private String mUrl;
    private HttpListener httpListener;
    private byte[] reuestDate;

    @Override
    public void setUrl(String url) {
        this.mUrl = url;
    }

    /**
     * 做耗时操作的能力请求
     * json image file
     */
    @Override
    public void excute() {
        //HttpUrlConnection请求
        //结果是Inputstream
        httpUrlConnection();

    }

    private void httpUrlConnection() {
        // 创建url资源
        URL url = null;
        try {
            url = new URL(mUrl);
            // 建立http连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 设置允许输出
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 设置不用缓存
            conn.setUseCaches(false);
            // 设置传递方式
            conn.setRequestMethod("POST");
            // 设置维持长连接
            conn.setRequestProperty("Connection", "Keep-Alive");
            // 设置文件字符集:
            conn.setRequestProperty("Charset", "UTF-8");
            //转换为字节数组
            byte[] data = reuestDate;
            // 设置文件长度
            conn.setRequestProperty("Content-Length", String.valueOf(data.length));
            // 设置文件类型:
            conn.setRequestProperty("contentType", "application/json");
            // 开始连接请求
            conn.connect();
            OutputStream out = conn.getOutputStream();
            // 写入请求的字符串
            out.write(reuestDate);
            out.flush();
            out.close();
            // 请求返回的状态
            if (conn.getResponseCode() == 200) {
                // 请求返回的数据
                InputStream in = conn.getInputStream();
                try {
                    byte[] data1 = new byte[in.available()];
                    in.read(data1);
                    httpListener.onSuccess(in);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            } else {
                httpListener.onFail();
            }

        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void setRequest(byte[] requestDate) {
        this.reuestDate = requestDate;
    }

    @Override
    public void setHttpCallBack(HttpListener httpListener) {
        this.httpListener = httpListener;
    }
}
