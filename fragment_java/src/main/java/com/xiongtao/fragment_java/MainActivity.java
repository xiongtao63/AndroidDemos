package com.xiongtao.fragment_java;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if(savedInstanceState == null){
            //TODO: 1.获取fragmentManager
            FragmentManager fragmentManager = getSupportFragmentManager();
            //TODO: 2.开启一个fragment事务
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            //TODO: 3.向FrameLayout容器添加MainFragment,现在并未真正执行
            transaction.add(R.id.frameLayout, MainFragment.newIntance(), MainFragment.class.getName());


            //TODO: 4.提交事务，真正去执行添加动作
            transaction.commit();
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("Zero", "name: " + MainActivity.class.getName() + " -> " + "onSaveInstanceState");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Log.i("Zero", "MainActivity onOptionsItemSelected: "+item );
        return super.onOptionsItemSelected(item);
//       return true;
    }
}