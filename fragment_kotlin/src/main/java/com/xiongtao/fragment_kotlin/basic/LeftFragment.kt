package com.xiongtao.fragment_kotlin.basic

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xiongtao.fragment_kotlin.LifeCycleFragment
import com.xiongtao.fragment_kotlin.R

class LeftFragment:LifeCycleFragment() {
    //这是静态fragment的使用方式

    //嵌套了fragment
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_left,container,false)
    }

    fun showMsg(view: View) {
        Log.i(TAG,"show left fragment")
    }
}