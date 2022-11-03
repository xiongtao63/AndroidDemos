package com.xiongtao.retrofit_java.ui.login;

import com.xiongtao.retrofit_java.base.BaseModel;
import com.xiongtao.retrofit_java.base.BasePresenter;
import com.xiongtao.retrofit_java.base.BaseView;

import io.reactivex.Observable;

public interface LoginContract {
    interface Presenter extends BasePresenter{
        void login(String mobile,String password);
    }
    interface View extends BaseView<Presenter>{
        void loginSuccess(String result);
    }
    interface Model extends BaseModel{
        Observable<String> login(String mobile,String password);

    }
}
