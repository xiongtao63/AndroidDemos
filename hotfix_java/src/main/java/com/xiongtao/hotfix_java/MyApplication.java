package com.xiongtao.hotfix_java;

import android.app.Application;
import android.content.Context;

import com.xiongtao.hotlib.EnjoyFix;


public class MyApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        //
        EnjoyFix.installPatch(this,"/sdcard/patch.jar");
    }
}