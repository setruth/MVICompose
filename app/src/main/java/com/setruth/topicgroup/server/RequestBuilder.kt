package com.setruth.topicgroup.server

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

/**
 * @author  :Setruth
 * time     :2022/8/26 9:12
 * e-mail   :1607908758@qq.com
 * remark   :TopicGroup
 */

class RequestBuilder<T>(context: Context, requestConfig: Class<T>) {
    var requestApi: T

    init {
        OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .build()
            .apply {
                Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(this)
                    .build()
                    .apply {
                        requestApi=this.create(requestConfig)
                    }
            }
    }

     companion object{
         const val REQUEST_OK = 200
         const val REQUEST_ERR = 500
        const val BASE_URL="http://10.0.2.2:717"
    }
}