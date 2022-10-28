package com.xiongtao.fragment_java.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiongtao.fragment_java.BaseFragment;
import com.xiongtao.fragment_java.FragmentDelegater;
import com.xiongtao.fragment_java.R;

/**
 * TODO: Can not perform this action after onSaveInstanceState
 */
public class Bug2Fragment extends BaseFragment {

    private Activity mActivity;
    private Handler mHandler;

    public static Fragment newIntance(Handler handler) {
        Bug2Fragment fragment = new Bug2Fragment();
        fragment.setHandler(handler);
        fragment.setFragmentDelegater(new FragmentDelegater(fragment));
        return fragment;
    }

    public void setHandler(Handler handler){
        mHandler = handler;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bug2, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    public void run(){
                        while (true){
                            try {
                                Thread.sleep(5000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            mHandler.sendEmptyMessage(0);
                        }
                    }
                }.start();
            }
        });

    }

}
