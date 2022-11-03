package com.xiongtao.retrofit_java.ui.login;

import com.xiongtao.retrofit_java.base.AbsPresenter;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter extends AbsPresenter<LoginContract.Model,LoginContract.View> implements LoginContract.Presenter {

    private Disposable disposable;
    @Override
    public void setModel(LoginContract.Model mModel) {
        this.mModel = mModel;
    }

    @Override
    public void setView(LoginContract.View mView) {
        this.mView = mView;
        mView.setPresenter(this);

    }

    @Override
    public void start() {
        this.mView.setPresenter(this);
    }

    @Override
    public void onDestory() {
        mView = null;
        if(disposable !=null && !disposable.isDisposed()){
            disposable.dispose();
            disposable = null;
        }
    }

    @Override
    public void login(String mobile, String password) {
        disposable = mModel.login(mobile,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mView::loginSuccess);
    }
}
