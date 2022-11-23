package com.xiongtao.iocdemo

import android.view.View
import java.lang.reflect.Proxy


object InjectUtils {
    fun inject(context:Any){
        //布局的注入
        injectLayout(context)
        //控件注入
        injectView(context)
        //事件注入
        injectClick(context)

    }

    private fun injectClick(obj: Any) {
        //需要一次性处理安卓中23种事件
        val clazz = obj.javaClass
        val methods = clazz.declaredMethods
        methods.forEach {method ->
            //注意，别把代码写死了 method.getAnnotation(OnClick.class);
            val annotations = method.annotations
            for (annotation in annotations) {
                //annotation是事件比如onClick 就去取对应的注解
                val annotationType  = annotation.annotationClass.java
                val eventBase = annotationType.getAnnotation(EventBase::class.java)
                if(eventBase==null){  //如果没有eventBase，则表示当前方法不是一个事件处理的方法
                    continue
                }
                //否则就是一个事件处理的方法
                //开始获取事件处理的相关信息（三要素）
                //用于确定是哪种事件
//                btn.setOnClickListener（new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                    }
//                });
                //1.setOnClickListener 订阅关系
//                String listenerSetter();
                val listenerSetter = eventBase.listenerSetter
                //2.new View.OnClickListener()  事件本身
//                Class<?> listenerType();
                val listenerType = eventBase.listenerType.java
                //3.事件处理程序
//                String callbackMethod();
                val callbackMethod = eventBase.callbackMethod

                val valueMethod = annotationType.getDeclaredMethod("value")
                //反射得到id,再根据ID号得到对应的VIEW（Button）
                val viewId = valueMethod.invoke(annotation) as IntArray
                for (id in viewId) {
                    val findViewById = clazz.getMethod("findViewById", Int::class.java)
                    val view = findViewById.invoke(obj, id) as View
                    //运行到这里，view就相到于我们写的Button
                    if(view == null){
                        continue
                    }
//activity==context    click===method
                    val listenerInvocationHandler = ListenerInvocationHandler(obj, method)
                    //做代理   new View.OnClickListener()对象
                    val newProxyInstance = Proxy.newProxyInstance(
                        listenerType.classLoader,
                        arrayOf(listenerType),
                        listenerInvocationHandler
                    )

                    //执行  让proxy执行的onClick()
                    //参数1  setOnClickListener（）
                    //参数2  new View.OnClickListener()对象
                    //   view.setOnClickListener（new View.OnClickListener()）
                    val onClickMethod = view.javaClass.getMethod(listenerSetter, listenerType)
                    onClickMethod.invoke(view,newProxyInstance)
                    //这时候，点击按钮时就会去执行代理类中的invoke方法()
                }



            }

        }

    }

    private fun injectView(context: Any) {
        val javaClass = context.javaClass
        val fields = javaClass.fields
        fields.forEach {
            val viewInject = it.getAnnotation(ViewInject::class.java)
            if(viewInject != null){
                val valueId = viewInject.value
                //运行到这里，每个按钮的ID已经取到了
                //注入就是反射执行findViewById方法
                val method = javaClass.getMethod("findViewById", Int::class.java)
                val view = method.invoke(context, valueId)
                it.isAccessible = true
                it.set(context,view)
            }
        }

    }

    private fun injectLayout(context: Any) {
        var layoutId = 0
        val javaClass = context.javaClass
        val annotation = javaClass.getAnnotation(ContentView::class.java)
        if(annotation != null){
            //取到注解括号后面的内容
            layoutId = annotation.value
            //反射去执行setContentView
            val method = javaClass.getMethod("setContentView", Int::class.java)
            method.invoke(context,layoutId)
        }

    }
}