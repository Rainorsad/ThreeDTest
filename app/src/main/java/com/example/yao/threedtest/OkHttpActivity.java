package com.example.yao.threedtest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by Yao on 2016/11/7.
 */
public class OkHttpActivity extends AppCompatActivity{
    private Button bt,bt_postcan;
    private OkHttpClient ok;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        ok = new OkHttpClient();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);

        bt = (Button) findViewById(R.id.okhttp_test);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetNOCanbegin();
            }
        });
        bt_postcan = (Button) findViewById(R.id.okhttp_postcan);
        bt_postcan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PostCanbegin();
            }
        });
    }

    /**
     * Post请求带参数
     */
    private void PostCanbegin() {
        String url = "http://118.244.212.82:9092/newsClient/user_login?";
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("ver","1");
        builder.add("uid","123");
        builder.add("pwd","123456");
        builder.add("device","0");
        Request r = new Request.Builder().url(url).post(builder.build()).build();
        ok.newCall(r).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String data = response.body().string();
                Log.d("测试",data);
            }
        });
    }

    /**
     * get请求不带参数
     */
    private void GetNOCanbegin() {

        Request request = new Request.Builder().url("http://118.244.212.82:9092/newsClient/news_list?ver=1&subid=1&dir=1&nid=1&stamp=20140321&cnt=20")
                .build();
        Call call = ok.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String data = response.body().string();
                Log.d("测试",data);
            }
        });
    }
}
