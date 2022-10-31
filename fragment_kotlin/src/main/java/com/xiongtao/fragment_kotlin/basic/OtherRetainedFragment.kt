package com.xiongtao.fragment_kotlin.basic

import android.os.Bundle
import com.xiongtao.fragment_kotlin.LifeCycleFragment

class OtherRetainedFragment:LifeCycleFragment() {

    var data: MyAsyncTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

}