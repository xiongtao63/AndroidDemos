package com.xiongtao.retrofit_mvp_kotlin.login

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.xiongtao.retrofit_mvp_kotlin.R
import com.xiongtao.retrofit_mvp_kotlin.beans.LoginBean
import kotlinx.android.synthetic.main.activity_main.*

//import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity:AppCompatActivity() ,LoginView{
    private val loginPresenter by lazy { LoginPresenterImpl(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

     login.setOnClickListener{
         loginPresenter.login(LoginActivity@this,username.text.toString(),password.text.toString())
     }
    }

    override fun loginSuccess(loginBean: LoginBean) {
        TODO("Not yet implemented")
    }

    override fun loginFailure(errorMsg: String) {
        TODO("Not yet implemented")
    }
}