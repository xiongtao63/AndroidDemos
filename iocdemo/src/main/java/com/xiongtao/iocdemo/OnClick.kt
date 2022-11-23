package com.xiongtao.iocdemo

import android.view.View

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@EventBase("setOnClickListener", View.OnClickListener::class,"onClick")
annotation class OnClick(vararg val value:Int = [-1])
