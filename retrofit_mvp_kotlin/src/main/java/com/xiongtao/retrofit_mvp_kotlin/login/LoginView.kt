package com.xiongtao.retrofit_mvp_kotlin.login

import com.xiongtao.retrofit_mvp_kotlin.beans.LoginBean

interface LoginView {
    fun loginSuccess(loginBean: LoginBean)
    fun loginFailure(errorMsg:String)
}