package com.xiongtao.retrofit_java.ui.login;

import com.xiongtao.retrofit_java.network.api.WanAndroidApi;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class LoginModel implements LoginContract.Model{
    private WanAndroidApi wanAndroidApi;
    public void setWanAndroidApi(WanAndroidApi wanAndroidApi){
        this.wanAndroidApi = wanAndroidApi;
    }
    @Override
    public Observable<String> login(String mobile, String password) {
//        return wanAndroidApi.login(mobile,password).map(baseResponse -> baseResponse.getData().toString())
//                .toObservable();
        return Observable.just("登录成功"+mobile).delay(1000, TimeUnit.MILLISECONDS);
    }
}
