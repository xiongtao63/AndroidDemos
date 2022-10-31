package com.xiongtao.fragment_kotlin.basic

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.xiongtao.fragment_kotlin.R


class BasicDemoActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic)
        Log.i("Zero","${this::class.simpleName} onCreate")
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Log.i("Zero","${this::class.simpleName} onConfigurationChanged")
    }



}