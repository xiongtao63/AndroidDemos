package com.xiongtao.retrofit_mvp_kotlin.login

import android.content.Context

interface LoginPresenter {
    fun login(context: Context, username:String, password: String)
}