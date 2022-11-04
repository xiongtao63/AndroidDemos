package com.xiongtao.retrofit_mvp_kotlin.network

import android.content.Context
import android.database.Observable
import com.google.gson.Gson
import com.xiongtao.retrofit_mvp_kotlin.beans.ResponseWrapper
import com.xiongtao.retrofit_mvp_kotlin.view.LoadingDialog
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class ApiResponse<T> (private val context: Context) : Observer<ResponseWrapper<out T>> {
    private var isShowLoading = false
    constructor(context: Context,isShowLoading:Boolean = false) : this(context){
        this.isShowLoading = isShowLoading
    }

    override fun onSubscribe(d: Disposable) {
        if(isShowLoading){
            LoadingDialog.show(context)
        }
    }

    override fun onNext(t: ResponseWrapper<out T>) {
        success(t.data)
    }

    override fun onError(e: Throwable) {
        LoadingDialog.cancle()
        if(e is HttpException){
            val apiError: ApiError = when(e.code()){
                ApiErrorType.INTERNAL_SERVER_ERROR.code ->
                    ApiErrorType.INTERNAL_SERVER_ERROR.getApiError(context)
                ApiErrorType.BAD_GATEWAY.code ->
                    ApiErrorType.BAD_GATEWAY.getApiError(context)
                ApiErrorType.NOT_FOUND.code ->
                    ApiErrorType.NOT_FOUND.getApiError(context)
                else -> otherError(e)
            }
            failure(e.code(),apiError)
            return
        }

        val apiErrorType:ApiErrorType = when(e){
            is UnknownHostException -> ApiErrorType.NETWORK_NOT_CONNECT
            is ConnectException -> ApiErrorType.NETWORK_NOT_CONNECT
            is SocketTimeoutException -> ApiErrorType.CONNECT_TIMEOUT
            else -> ApiErrorType.UNKNOWN_ERROR
        }
        failure(apiErrorType.code,apiErrorType.getApiError(context))
    }

    private fun otherError(e: HttpException): ApiError =
        Gson().fromJson(e.response()?.errorBody()?.charStream(),ApiError::class.java)

    abstract fun success(data:T)
    abstract fun failure(statusCode:Int,apiError: ApiError)


}