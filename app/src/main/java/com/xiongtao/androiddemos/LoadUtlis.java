package com.xiongtao.androiddemos;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.lang.reflect.Method;

public class LoadUtlis {
    private static Resources mResouces;

    public static Resources getResource(Context context){
        if(mResouces == null){
            return loadResource(context);
        }
        return mResouces;
    }

    private static Resources loadResource(Context context) {
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPath = assetManager.getClass().getDeclaredMethod("addAssetPath", String.class);
            addAssetPath.setAccessible(true);
            addAssetPath.invoke(assetManager,context.getCacheDir().getPath()+"/app-debug.apk");
            Resources resources = context.getResources();
            return new Resources(assetManager,resources.getDisplayMetrics(),resources.getConfiguration());
        }  catch (Exception e) {
            e.printStackTrace();

        }
        return null;

    }
}
