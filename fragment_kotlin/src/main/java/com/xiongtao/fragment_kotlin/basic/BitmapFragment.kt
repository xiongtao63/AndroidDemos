package com.xiongtao.fragment_kotlin.basic

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xiongtao.fragment_kotlin.LifeCycleFragment
import com.xiongtao.fragment_kotlin.R

class BitmapFragment:LifeCycleFragment() {

    var data:Bitmap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }
}