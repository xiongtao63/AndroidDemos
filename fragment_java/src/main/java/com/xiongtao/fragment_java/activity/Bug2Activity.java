package com.xiongtao.fragment_java.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.xiongtao.fragment_java.MainActivity;
import com.xiongtao.fragment_java.R;
import com.xiongtao.fragment_java.fragment.Bug2Fragment;

/**
 * TODO: Can not perform this action after onSaveInstanceState
 */
public class Bug2Activity extends FragmentActivity {

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.frameLayout, Bug2Fragment.newIntance(mHandler), Bug2Fragment.class.getName());
            transaction.commitAllowingStateLoss();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bug2);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.frameLayout, Bug2Fragment.newIntance(mHandler), Bug2Fragment.class.getName());
        transaction.commit();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.i("Zero", "name: " + Bug2Activity.class.getName() + " -> " + "onSaveInstanceState");
    }

    //    解决方法：
//            1、该事务使用commitAllowingStateLoss()方法提交，但是有可能导致该次提交无效！（宿主Activity被强杀时）
//    对于popBackStack()没有对应的popBackStackAllowingStateLoss()方法，所以可以在下次可见时提交事务，参考2
//
//             2、利用onActivityForResult()/onNewIntent()，可以做到事务的完整性，不会丢失事务


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i("Zero", "name: " + Bug2Activity.class.getName() + " -> " + "onNewIntent");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("Zero", "name: " + Bug2Activity.class.getName() + " -> " + "onActivityResult");
    }
}
