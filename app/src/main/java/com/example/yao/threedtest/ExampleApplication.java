package com.example.yao.threedtest;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Yao on 2016/11/15.
 */
public class ExampleApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }

}
