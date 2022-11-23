package com.xiongtao.iocdemo

import android.view.View

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@EventBase("setOnLongClickListener", View.OnLongClickListener::class,"onLongClick")
annotation class OnLongClick(vararg val value:Int = [-1])
