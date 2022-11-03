package com.xiongtao.retrofit_java.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment <P extends BasePresenter> extends Fragment {
    protected final String TAG = this.getClass().getSimpleName();
    protected P mPresenter;
    private Unbinder mUnbinder;
    protected BaseActivity mActivity;
    protected View mRootView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(layoutId(),container,false);
        mUnbinder = ButterKnife.bind(this,mRootView);
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mActivity = (BaseActivity) getActivity();
        initData();
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mPresenter!=null){
            mPresenter.start();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(mUnbinder != Unbinder.EMPTY) mUnbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mPresenter = null;
        this.mActivity = null;
        this.mRootView = null;
        this.mUnbinder = null;
    }

    protected abstract int layoutId();
    protected abstract void initData();
}
