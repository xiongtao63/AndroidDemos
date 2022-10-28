package com.xiongtao.fragment_java.activity;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.xiongtao.fragment_java.LoadingDialog;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * TODO: Fragment重叠异常
 */
public class SaveDataActivity extends ListActivity {
    private ListAdapter mAdapter;
    private static final String TAG = "Zero";
    private ArrayList<String> datas;
    private LoadDataAsyncTask loadDataAsyncTask;
    private LoadingDialog loadingDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate");
        initData(savedInstanceState);
    }

    private void initData(Bundle savedInstanceState) {
        if(savedInstanceState != null){
            datas = savedInstanceState.getStringArrayList("mDatas");
        }else{
            loadingDialog = new LoadingDialog();
            loadingDialog.show(this);
            loadDataAsyncTask = new LoadDataAsyncTask();
            loadDataAsyncTask.execute();
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle state) {
        super.onRestoreInstanceState(state);
        Log.e(TAG, "onRestoreInstanceState");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e(TAG, "onSaveInstanceState");
        outState.putSerializable("mDatas",datas);
    }

    private class LoadDataAsyncTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            datas = generateTimeConsumingDatas();
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            loadingDialog.cancle();
            initAdapter();
        }

        private ArrayList<String> generateTimeConsumingDatas() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return new ArrayList<>(
                    Arrays.asList("通过Fragment保存大量数据",
                            "onSaveInstanceState保存数据",
                            "getLastNonConfigurationInstance已经被弃用", "RabbitMQ", "Hadoop",
                            "Spark")
            );
        }
    }

    private void initAdapter() {
        mAdapter = new ArrayAdapter<String>(SaveDataActivity.this, android.R.layout.simple_list_item_1,datas);
        setListAdapter(mAdapter);

    }

    @Override
    protected void onDestroy()
    {
        Log.e(TAG, "onDestroy");
        super.onDestroy();
    }
}
