package com.xiongtao.retrofit_mvp_kotlin.api

import com.xiongtao.retrofit_mvp_kotlin.beans.LoginBean
import com.xiongtao.retrofit_mvp_kotlin.beans.RegisterBean
import com.xiongtao.retrofit_mvp_kotlin.beans.ResponseWrapper
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface WanAndroidApi {
    @FormUrlEncoded
    @POST("user/login")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Observable<ResponseWrapper<LoginBean>>

    @FormUrlEncoded
    @POST("user/register")
    fun register(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("repassword") repassword: String
    ): Observable<ResponseWrapper<RegisterBean>>
}