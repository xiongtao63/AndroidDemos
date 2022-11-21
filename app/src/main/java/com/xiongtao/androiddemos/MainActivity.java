package com.xiongtao.androiddemos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import java.util.zip.Inflater;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("leo", "onCreate: 启动了插件的Activity");
//        setContentView(R.layout.activity_main);
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.activity_main, null);
        setContentView(inflate);
    }
}