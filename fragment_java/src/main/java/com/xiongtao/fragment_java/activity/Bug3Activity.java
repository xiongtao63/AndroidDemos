package com.xiongtao.fragment_java.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.xiongtao.fragment_java.FragmentDelegater;
import com.xiongtao.fragment_java.R;
import com.xiongtao.fragment_java.fragment.Bug1Fragment;
import com.xiongtao.fragment_java.fragment.Bug2Fragment;
import com.xiongtao.fragment_java.fragment.Bug31Fragment;
import com.xiongtao.fragment_java.fragment.Bug3Fragment;
import com.xiongtao.fragment_java.fragment.Bug4Fragment;

/**
 * TODO: Fragment重叠异常
 */
public class Bug3Activity extends FragmentActivity {
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.frameLayout, Bug2Fragment.newIntance(mHandler),Bug3Fragment.class.getName());
            fragmentTransaction.commit();
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bug3);
        if(savedInstanceState == null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.frameLayout,Bug3Fragment.newInstance(mHandler),Bug3Fragment.class.getName());
            fragmentTransaction.commit();

            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.frameLayout,Bug2Fragment.newIntance(mHandler),Bug2Fragment.class.getName());
            fragmentTransaction.commit();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    /**
     * 原因分析
     * FragmentActivity 帮我们保持了fragment的状态
     * 但是在重启后恢复时，视图的可见状态没帮我们保存，而Fragment默认的是show状态，所以产生了Fragment重叠现象
     * FragmentActivity -> FragmentController -> restoreAllState -> FragmentManagerImpl.restoreAllState()
     *  Fragment f = fs.instantiate(mHost, mParent);
     *  系统帮我们保存的Fragment其实最终是以FragmentState形式存在
     *  为什么在页面重启后会发生Fragment的重叠？ 其实答案已经很明显了，根据上面的源码分析，我们会发现FragmentState里没有Hidden状态的字段
     *  使用show , hide控制Fragment
     * 我们使用show(),hide()时，都是使用add的方式加载Fragment的，add配合hide使Fragment的视图改变为GONE状态；而replace是销毁Fragment 的视图。
     * 页面重启时，add的Fragment会全部走生命周期，创建视图；而replace的非栈顶Fragment不会走生命周期，只有Back时，才会逐一走栈顶Fragment生命周期，创建视图。
     * 结合上面的源码分析，在使用replace加载Fragment时，页面重启后，Fragment视图都还没创建，所以mHidden没有意义，不会发生重叠现象；
     * 而在使用add加载时，视图是存在的并且叠加在一起，页面重启后 mHidden=false，所有的Fragment都会是show状态显示出来（即VISIBLE），从而造成了Fragment重叠！
     */
}
