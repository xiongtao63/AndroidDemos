package com.xiongtao.retrofit_mvp_kotlin.login

import android.content.Context
import com.xiongtao.retrofit_mvp_kotlin.beans.LoginBean

class LoginPresenterImpl(val loginView: LoginView):LoginPresenter,LoginModule.CallBack{
    private val loginModule = LoginModuleImpl()
    override fun login(context: Context, username: String, password: String) {
        loginModule.login(context,username,password,this)
    }

    override fun loginSuccess(loginBean: LoginBean) {

    }
}