package com.xiongtao.retrofit_mvp_kotlin.beans

data class ResponseWrapper<out T>(val errorCode:Int,val errorMsg:String,val data:T)
data class LoginBean(
    var admin: Boolean, var chapterTops: List<*>, var collectIds: List<*>
    , var email: String?, var icon: String?, var id: String?, var nickname: String?
    , var password: String?, var publicName: String?, var token: String?
    , var type: Int, var username: String?
)

data class RegisterBean(
    var admin: Boolean, var chapterTops: List<*>, var collectIds: List<*>
    , var email: String?, var icon: String?, var id: String?, var nickname: String?
    , var password: String?, var publicName: String?, var token: String?
    , var type: Int, var username: String?
)
