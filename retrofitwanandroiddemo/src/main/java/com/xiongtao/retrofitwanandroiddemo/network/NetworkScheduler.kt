package com.xiongtao.retrofitwanandroiddemo.network

import io.reactivex.ObservableTransformer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object NetworkScheduler {
    fun <T> compose():ObservableTransformer<T,T>{
        return ObservableTransformer {
            observable ->
            observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

        }
    }
}