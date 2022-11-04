package com.xiongtao.retrofit_mvp_kotlin.login

import android.content.Context
import com.xiongtao.retrofit_mvp_kotlin.beans.LoginBean

interface LoginModule {

    interface CallBack{
        fun loginSuccess(loginBean: LoginBean)
    }

    fun login(context: Context,username:String,password:String,callback: CallBack)
    fun register()
}