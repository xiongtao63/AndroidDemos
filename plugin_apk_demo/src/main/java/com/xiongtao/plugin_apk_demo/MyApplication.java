package com.xiongtao.plugin_apk_demo;

import android.app.Application;

public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        LoadUtil.loadClass(this);

    }
}