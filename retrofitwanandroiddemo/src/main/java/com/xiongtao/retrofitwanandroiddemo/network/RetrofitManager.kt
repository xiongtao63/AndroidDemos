package com.xiongtao.retrofitwanandroiddemo.network

import android.util.Log
import com.xiongtao.retrofitwanandroiddemo.calladapter.LiveDataCallAdapterFactory
import com.xiongtao.retrofitwanandroiddemo.converter.MyGsonConvertFactory
import io.reactivex.android.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitManager private constructor(){
    companion object{
        const val TAG = "Zero"
        private val INSTANCE = RetrofitManager()
        fun getInstance() = INSTANCE;
    }

    internal class HttpLogger:HttpLoggingInterceptor.Logger{
        private val mMessage = StringBuffer()
        override fun log(message: String) {
            var message = message
            if(message.startsWith("--> POST")){
                mMessage.setLength(0)
            }
            val isJson = (message.startsWith("{") && message.endsWith("}"))
                    || (message.startsWith("[") && message.endsWith("]"))
            if(isJson){
                message = JsonUtil.formatJson(message);
            }
            mMessage.append(message+"\n")
            if(message.startsWith("<-- END HTTP")){
                Log.d("Zero", mMessage.toString())
            }


        }

    }

    fun createRetrofit(baseUrl:String): Retrofit {
        val okHttpClient = OkHttpClient().newBuilder().apply {
            addInterceptor(HttpLoggingInterceptor().setLevel(
                if(BuildConfig.DEBUG) {HttpLoggingInterceptor.Level.BODY}
                    else HttpLoggingInterceptor.Level.NONE
            ))
        }.build()
        return Retrofit.Builder()
            .baseUrl(baseUrl)
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory.create())
            .addConverterFactory(MyGsonConvertFactory.create())
            .client(okHttpClient)
            .build()
    }

    fun <T> getService(service: Class<T>,baseUrl: String = "https://www.wanandroid.com/"):T = createRetrofit(baseUrl).create(service)
}