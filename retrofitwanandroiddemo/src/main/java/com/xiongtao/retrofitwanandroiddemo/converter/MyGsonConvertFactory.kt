package com.xiongtao.retrofitwanandroiddemo.converter

import com.google.gson.Gson
import com.google.gson.JsonIOException
import com.google.gson.TypeAdapter
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonToken
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody
import okio.Buffer
import okio.Utf8
import retrofit2.Converter
import retrofit2.Retrofit
import java.io.IOException
import java.io.OutputStreamWriter
import java.lang.reflect.Type
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets.UTF_8

class MyGsonConvertFactory(val gson: Gson):Converter.Factory() {
    companion object{
        @JvmStatic
        fun create() = MyGsonConvertFactory(Gson())
        fun create(gson: Gson):MyGsonConvertFactory{
            if(gson==null)throw NullPointerException("gson == null")
            return MyGsonConvertFactory(gson)
        }
    }
    override fun requestBodyConverter(
        type: Type,
        parameterAnnotations: Array<out Annotation>,
        methodAnnotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<*, RequestBody>? {
        val adapter = gson.getAdapter(TypeToken.get(type))
        return MyGsonRequestBodyConverter(gson,adapter)
    }

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        val adapter = gson.getAdapter(TypeToken.get(type))
        return MyGsonResponseBodyConverter(gson,adapter)
    }

    private class MyGsonResponseBodyConverter<T>(
        private val gson: Gson,
        private val adapter: TypeAdapter<T>
    ):Converter<ResponseBody,T>{
        override fun convert(value: ResponseBody): T? {
            val jsonReader = gson.newJsonReader(value.charStream())
            return value.use { value ->
                val result =  adapter.read(jsonReader)
                if(jsonReader.peek() !== JsonToken.END_DOCUMENT){
                    throw JsonIOException("JSON document was not fully consumed.")
                }
                result
            }
        }

    }


    private class MyGsonRequestBodyConverter<T>(
        private val gson: Gson,
        private val adapter: TypeAdapter<T>
    ): Converter<T, RequestBody>{
        @Throws(IOException::class)
        override fun convert(value: T): RequestBody? {
            val buffer = Buffer()
            val writer = OutputStreamWriter(buffer.outputStream(), UTF_8)
            val jsonWriter = gson.newJsonWriter(writer)
            adapter.write(jsonWriter,value)
            jsonWriter.close()
            return RequestBody.create(MEDIA_TYPE,buffer.readByteString())

        }

        companion object {
            private val MEDIA_TYPE: MediaType = MediaType.get("application/json; charset=UTF-8")
            private val UTF_8: Charset = Charset.forName("UTF-8")
        }

    }
}