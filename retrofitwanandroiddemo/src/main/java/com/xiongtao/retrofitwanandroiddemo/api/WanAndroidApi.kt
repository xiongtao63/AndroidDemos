package com.xiongtao.retrofitwanandroiddemo.api

import com.xiongtao.retrofitwanandroiddemo.bean.LoginBean
import com.xiongtao.retrofitwanandroiddemo.bean.ResponseWrapper
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface WanAndroidApi {
    @POST("/user/login")
    @FormUrlEncoded
    fun loginWanAndroid(
        @Field("username") username:String,
        @Field("password") password:String
    ):Observable<ResponseWrapper<LoginBean>>

    @POST("/user/register")
    @FormUrlEncoded
    fun registerWanAndroid(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("repassword") repassword: String,
    ):Observable<ResponseWrapper<LoginBean>>
}