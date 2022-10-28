package com.xiongtao.fragment_java.activity;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import com.xiongtao.fragment_java.R;
import com.xiongtao.fragment_java.dialog.MyDialogFragment;


public class MyDialogActivity extends FragmentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mydialog);


    }

    public void showLoginDialog(View view)
    {
        MyDialogFragment myDialogFragment = MyDialogFragment.newInstance();
        myDialogFragment.show(getSupportFragmentManager(),"dialog");
    }

}
