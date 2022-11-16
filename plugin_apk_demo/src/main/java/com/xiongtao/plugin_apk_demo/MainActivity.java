package com.xiongtao.plugin_apk_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                privateClassLoader();

//                String path = getCacheDir().getPath()+"/test.dex";
//                DexClassLoader pathClassLoader = new DexClassLoader(path,
//                       null,null, MainActivity.this.getClassLoader());
//                try {
//                    Class<?> aClass = pathClassLoader.loadClass("com.xiongtao.androiddemos.Test");
//                    Method print = aClass.getMethod("print");
//                    print.invoke(null);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
                try {
                    Class<?> testClass = Class.forName("com.xiongtao.androiddemos.Test");
                    Method print = testClass.getMethod("print");
                    print.invoke(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void privateClassLoader() {
        ClassLoader classLoader = getClassLoader();
        while (classLoader != null) {
            Log.e("leo", "privateClassLoader: " + classLoader);
            classLoader = classLoader.getParent();
        }
        Log.e("leo", "privateClassLoader: " + Activity.class.getClassLoader());
        Log.e("leo", "privateClassLoader: " + AppCompatActivity.class.getClassLoader());

    }


}