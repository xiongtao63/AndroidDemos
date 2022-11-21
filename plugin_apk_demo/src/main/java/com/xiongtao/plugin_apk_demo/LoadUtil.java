package com.xiongtao.plugin_apk_demo;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;

public class LoadUtil {

//    private static String apkPath =
    public static void loadClass(Context context){
        try {
            //获取BaseDexClassLoader 里面的pathList
            Class<?> baseDexClassLoader = Class.forName("dalvik.system.BaseDexClassLoader");
            Field pathListField  = baseDexClassLoader.getDeclaredField("pathList");
            pathListField.setAccessible(true);

//DexPathList dexElements
            Class<?> dexPathList = Class.forName("dalvik.system.DexPathList");
            Field dexElementsField = dexPathList.getDeclaredField("dexElements");
            dexElementsField.setAccessible(true);

            /**
             * 插件
             */
            // 创建插件的 DexClassLoader 类加载器，然后通过反射获取插件的 dexElements 值
            // 插件的
            DexClassLoader dexClassLoader = new DexClassLoader(
                    context.getCacheDir().getPath()+"/app-debug.apk",
                    context.getCacheDir().getAbsolutePath(),
                    null,context.getClassLoader());
            Object pluginPathList  = pathListField.get(dexClassLoader);
            // 拿到了插件的 dexElements
            Object[] pluginDexElements  =  (Object[])dexElementsField.get(pluginPathList);


            /**
             * 宿主
             */
            PathClassLoader pathClassLoader = (PathClassLoader) context.getClassLoader();
            Object hostPathList = pathListField.get(pathClassLoader);
            Object[] hostDexElements =(Object[]) dexElementsField.get(hostPathList);

            /**
             * 创建数组
             *
             */
            Object[] dexElements  = (Object[])Array.newInstance(pluginDexElements.getClass().getComponentType(),
                    pluginDexElements.length + hostDexElements.length);
            System.arraycopy(hostDexElements,0,dexElements,0,hostDexElements.length);
            System.arraycopy(pluginDexElements,0,dexElements,hostDexElements.length,pluginDexElements.length);

            // // 将创建的 dexElements 放到宿主的 dexElements中
            //            // 宿主的dexElements = 新的dexElements
            dexElementsField.set(hostPathList,dexElements);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Resources loadResources(Context context){
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPathMethod  = assetManager.getClass().getDeclaredMethod("addAssetPath", String.class);
            addAssetPathMethod.setAccessible(true);
            addAssetPathMethod.invoke(assetManager,context.getCacheDir().getPath()+"/app-debug.apk");
            Resources resources = context.getResources();
            return new Resources(assetManager,resources.getDisplayMetrics(),resources.getConfiguration());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
