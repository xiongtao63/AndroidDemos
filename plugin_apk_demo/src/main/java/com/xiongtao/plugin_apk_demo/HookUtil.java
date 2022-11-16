package com.xiongtao.plugin_apk_demo;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class HookUtil {

    private static final String TARGET_INTENT = "target_intent";
    public static void hookAMS(){
        try {
            // 获取Singleton对象
            Class<?> aClass = Class.forName("android.app.ActivityManager");
            Field singletonField  = aClass.getDeclaredField("IActivityManagerSingleton");
            singletonField.setAccessible(true);
            Object singleton  = singletonField.get(null);

            // 获取IActivityManager 对象
            Class<?> singletonClass = Class.forName("android.util.Singleton");
            Field mInstanceField  = singletonClass.getDeclaredField("mInstance");
            mInstanceField.setAccessible(true);
            Object mInstance = mInstanceField.get(singleton);

            Class<?> iActivityManagerClass = Class.forName("android.app.IActivityManager");

            Object proxyInstance  = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{iActivityManagerClass}, new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    // IActivityManager 的方法执行的时候，都会先跑这儿

                    System.out.println("leo====="+method.getName());
                    if("startActivity".equals(method.getName())){
                        int index = 0;
                        for (int i = 0; i < args.length; i++) {
                            if (args[i] instanceof Intent){
                                index = i;
                                break;
                            }
                        }
                        // 启动插件的intent
                        Intent intent = (Intent) args[index];

                        Intent proxyIntent = new Intent();
                        proxyIntent.setClassName("com.xiongtao.plugin_apk_demo",
                                "com.xiongtao.plugin_apk_demo.ProxyActivity");

                        proxyIntent.putExtra(TARGET_INTENT,intent);//保存原来的
                        args[index] = proxyIntent;
                    }
                    return method.invoke(mInstance,args);
                }
            });
            // 替换系统的 IActivityManager对象
            mInstanceField.set(singleton,proxyInstance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void hookHandler(){
        try {
            Class<?> aClass = Class.forName("android.app.ActivityThread");
            Field sCurrentActivityThread = aClass.getDeclaredField("sCurrentActivityThread");
            sCurrentActivityThread.setAccessible(true);
            Object activityThread  = sCurrentActivityThread.get(null);

            Field mHField  = aClass.getDeclaredField("mH");
            mHField.setAccessible(true);
            Object mH  = mHField.get(activityThread);

            Class<?> handlerClass = Class.forName("android.os.Handler");
            Field mCallbackField  = handlerClass.getDeclaredField("mCallback");
            mCallbackField.setAccessible(true);
            mCallbackField.set(mH, new Handler.Callback() {
                @Override
                public boolean handleMessage(@NonNull Message msg) {
                    // 将Intent换回来
                    switch (msg.what){
                        case 100:
                            try {
                                //  final ActivityClientRecord r = (ActivityClientRecord) msg.obj;
                                // 获取ActivityClientRecord中的intent对象
                                Field intentField = msg.obj.getClass().getDeclaredField("intent");
                                intentField.setAccessible(true);
                                Intent proxyIntent = (Intent) intentField.get(msg.obj);
                                //拿到插件的intent
                                Intent intent= proxyIntent.getParcelableExtra(TARGET_INTENT);
                                proxyIntent.setComponent(intent.getComponent());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                    }
                    return false;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
