package com.xiongtao.iocdemo

import kotlin.reflect.KClass

@Target(AnnotationTarget.ANNOTATION_CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class EventBase(
    //    textView.setOnClickListener（new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//
//        }
//    });
    //1.setOnClickListener 订阅关系
    val listenerSetter: String,
    //2.new View.OnClickListener()  事件本身
    val listenerType: KClass<*>,
    //3.事件处理程序
    val callbackMethod: String
)
