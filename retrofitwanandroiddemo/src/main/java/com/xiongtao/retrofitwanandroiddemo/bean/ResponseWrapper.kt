package com.xiongtao.retrofitwanandroiddemo.bean

data class ResponseWrapper<out T> (val errorCode:Int,val data:T,val errorMsg:String)