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
 * TODO: Fragment重叠异常
 */
public class Bug3Fragment extends BaseFragment {

    private Activity mActivity;
    private Handler mHandler;

    public static Fragment newInstance(Handler handler) {

        Bundle args = new Bundle();

        Bug3Fragment fragment = new Bug3Fragment();
        fragment.setFragmentDelegater(new FragmentDelegater(fragment));
        fragment.setArguments(args);
        fragment.setmHandler(handler);
        return fragment;
    }

    public void setmHandler(Handler mHandler) {
        this.mHandler = mHandler;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bug3,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(){
                    @Override
                    public void run() {
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
