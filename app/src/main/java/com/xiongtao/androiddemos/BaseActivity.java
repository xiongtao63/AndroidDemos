package com.xiongtao.androiddemos;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;

import java.lang.reflect.Field;

public class BaseActivity extends AppCompatActivity {
    protected Context mContext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Resources resource = LoadUtlis.getResource(getApplication());
        mContext = new ContextThemeWrapper(getBaseContext(),0);


        try {
            Class<? extends Context> aClass = mContext.getClass();
            Field mResources = aClass.getDeclaredField("mResources");
            mResources.setAccessible(true);
            mResources.set(mContext,resource);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //    @Override
//    public Resources getResources() {
//        if(getApplication() != null && getApplication().getResources()!=null){
//            //这个实际返回的是我们自己创建的 resources
//            return getApplication().getResources();
//        }
//        return super.getResources();
//    }
}
