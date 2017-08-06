package com.wy.lwl.volley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.wy.lwl.volley.http.DateListener;
import com.wy.lwl.volley.http.RequestInfo;
import com.wy.lwl.volley.http.ResponseInfo;
import com.wy.lwl.volley.http.Volley;

public class MainActivity extends AppCompatActivity {
    private final static String URL = "http://gc.ditu.aliyun.com/regeocoding?l=39.938133,116.395739&type=001";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Volley.sendJSONREquest(new RequestInfo(), URL, ResponseInfo.class, new DateListener() {
            @Override
            public void onSuccess(Object o) {
                String result = JSONObject.toJSON(o).toString();
                Log.e("success-------->",result);
            }

            @Override
            public void onFail(String error) {
                Log.e("fail-------->",error);
            }
        });
    }
}
