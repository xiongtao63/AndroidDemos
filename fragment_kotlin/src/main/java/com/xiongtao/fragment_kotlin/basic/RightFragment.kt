package com.xiongtao.fragment_kotlin.basic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xiongtao.fragment_kotlin.LifeCycleFragment
import com.xiongtao.fragment_kotlin.R

class RightFragment:LifeCycleFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_right,container,false)
    }
}