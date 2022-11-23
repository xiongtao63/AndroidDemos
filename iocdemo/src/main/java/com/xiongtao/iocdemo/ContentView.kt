package com.xiongtao.iocdemo

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class ContentView(val value:Int = -1);
