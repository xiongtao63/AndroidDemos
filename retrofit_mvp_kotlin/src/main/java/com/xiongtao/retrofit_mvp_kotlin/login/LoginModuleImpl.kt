package com.xiongtao.retrofit_mvp_kotlin.login

import android.content.Context
import com.xiongtao.retrofit_mvp_kotlin.api.WanAndroidApi
import com.xiongtao.retrofit_mvp_kotlin.beans.LoginBean
import com.xiongtao.retrofit_mvp_kotlin.network.ApiError
import com.xiongtao.retrofit_mvp_kotlin.network.ApiResponse
import com.xiongtao.retrofit_mvp_kotlin.network.NetworkScheduler
import com.xiongtao.retrofit_mvp_kotlin.network.RetrofitClient

class LoginModuleImpl:LoginModule{
    override fun login(context: Context, username: String, password: String, callback: LoginModule.CallBack) {
      RetrofitClient.instance.getService(WanAndroidApi::class.java).login(username,password)
          .compose(NetworkScheduler.compose())
          .subscribe(object : ApiResponse<LoginBean>(context){
              override fun success(data: LoginBean) {
                  callback.loginSuccess(data)
              }

              override fun failure(statusCode: Int, apiError: ApiError) {
                  TODO("Not yet implemented")
              }

              override fun onComplete() {
                  TODO("Not yet implemented")
              }

          })
    }

    override fun register() {
        TODO("Not yet implemented")
    }

}