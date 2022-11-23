package com.xiongtao.iocdemo

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method

/**
 * 这个类用来代理new View.OnClickListener()对象
 * 并执行这个对象身上的onClick方法
 */
class ListenerInvocationHandler(//需要在onClick中执行activity.click();
    private val activity: Any, private val activityMethod: Method
) : InvocationHandler {
    /**
     * 就表示onClick的执行
     * 程序执行onClick方法，就会转到这里来
     * 因为框架中不直接执行onClick
     * 所以在框架中必然有个地方让invoke和onClick关联上
     */
    @Throws(Throwable::class)
    override fun invoke(proxy: Any, method: Method, args: Array<Any>?): Any? {
        //在这里去调用被注解了的click();
        return activityMethod.invoke(activity, *(args ?: emptyArray()))
    }
}