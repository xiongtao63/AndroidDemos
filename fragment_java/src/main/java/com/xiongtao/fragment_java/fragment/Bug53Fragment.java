package com.xiongtao.fragment_java.fragment;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiongtao.fragment_java.BaseFragment;
import com.xiongtao.fragment_java.FragmentDelegater;
import com.xiongtao.fragment_java.R;


public class Bug53Fragment extends BaseFragment {

    private String name = "null";

    public static int count = 0;

    public static Fragment newIntance(final String name) {
        Bug53Fragment fragment = new Bug53Fragment();
        fragment.setFragmentDelegater(new FragmentDelegater(fragment));
        count++;
        Bundle args = new Bundle();
        args.putString("name", name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.i("Zero","onAttach Bug53Fragment");
        name = getArguments().getString("name");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("Zero","onDetach Bug53Fragment");
    }


    @Override
    public void onInflate(Activity activity, AttributeSet attrs, Bundle savedInstanceState) {
        super.onInflate(activity, attrs, savedInstanceState);
        Log.i("Zero", "onInflate");
        FragmentUtils.getFragmentInfo(this);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        Log.i("Zero", "onCreateView");
//        FragmentUtils.getFragmentInfo(this);
        return inflater.inflate(R.layout.fragment_bug52, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView info = view.findViewById(R.id.fragmentstack);
//        Log.i("Zero", "name: " + name + " count: " + count);
        info.setText(name + " " + count);
    }

}
