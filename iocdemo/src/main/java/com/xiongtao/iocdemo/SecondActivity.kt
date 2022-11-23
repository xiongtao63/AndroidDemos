package com.xiongtao.iocdemo

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button

@ContentView(R.layout.activity_main)
class SecondActivity:BaseActivity() {

    @ViewInject(R.id.btn1)
    lateinit var btn1: Button

    @ViewInject(R.id.btn2)
    lateinit var btn2: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @OnClick(R.id.btn1,R.id.btn2)
    fun click(view: View){
        Log.e("dddddd", "dddddddddddddddddd")
    }

    @OnLongClick(R.id.btn1)
    fun longClick(view: View):Boolean {
        Log.e("aaaaaaaa", "aaaaaaaaaaaaaaaaaa")
        return true
    }
}