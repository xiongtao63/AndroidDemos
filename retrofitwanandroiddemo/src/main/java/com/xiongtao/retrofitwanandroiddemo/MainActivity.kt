package com.xiongtao.retrofitwanandroiddemo

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.xiongtao.retrofitwanandroiddemo.api.WanAndroidApi
import com.xiongtao.retrofitwanandroiddemo.bean.LoginBean
import com.xiongtao.retrofitwanandroiddemo.bean.ResponseWrapper
import com.xiongtao.retrofitwanandroiddemo.network.NetworkScheduler
import com.xiongtao.retrofitwanandroiddemo.network.RetrofitManager
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       val registerWanAndroid:Observable<ResponseWrapper<LoginBean>>  = RetrofitManager.getInstance().getService(WanAndroidApi::class.java)
            .registerWanAndroid("xsdsdfsad2", "123456", "123456")
        registerWanAndroid.compose(NetworkScheduler.compose())
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<ResponseWrapper<LoginBean>>{
            override fun onSubscribe(d: Disposable) {
                Log.d("rxjava", "方法：onSubscribe: ");
            }

            override fun onNext(t: ResponseWrapper<LoginBean>) {
                Log.d("rxjava", "方法：onNext: " + t);
            }

            override fun onError(e: Throwable) {
                Log.d("rxjava", "方法：onError: " + e.message);
            }

            override fun onComplete() {
                Log.d("rxjava", "方法：onComplete: ");
            }

        })
        print("===========dddd")

    }

}