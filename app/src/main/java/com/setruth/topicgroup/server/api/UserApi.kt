package com.setruth.topicgroup.server.api

import android.util.Log
import com.setruth.topicgroup.pojo.NetResponse
import com.setruth.topicgroup.pojo.PublicCallback
import com.setruth.topicgroup.pojo.UserInfo
import com.setruth.topicgroup.server.RequestBuilder
import com.setruth.topicgroup.server.config.UserConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @author  :Setruth
 * time     :2022/8/26 9:37
 * e-mail   :1607908758@qq.com
 * remark   :TopicGroup
 */

class UserApi(private val requestBuilder: RequestBuilder<UserConfig>) {
    private val api=requestBuilder.requestApi
    /**
     * TODO 登录请求
     *
     * @param loginInfo
     */
    fun loginAPI(loginInfo:Map<String,String>,responseCallback:PublicCallback){
        Log.e("TAG", "loginAPI:${loginInfo} ", )
        api.login(loginInfo).enqueue(object: Callback<NetResponse>{
            override fun onResponse(call: Call<NetResponse>, response: Response<NetResponse>) {
                Log.e("TAG", "onResponse:Ok ", )
                responseCallback.response(RequestBuilder.REQUEST_OK,response.body())
            }

            override fun onFailure(call: Call<NetResponse>, t: Throwable) {
                Log.e("TAG", "onFailure:$t ", )
                responseCallback.response(RequestBuilder.REQUEST_ERR,null)
            }

        })
    }

    /**
     * TODO 注册请求
     *
     * @param registerInfo
     * @param responseCallback
     */
    fun registerAPI(registerInfo:UserInfo,responseCallback:PublicCallback){
        api.register(registerInfo).enqueue(object :Callback<NetResponse>{
            override fun onResponse(call: Call<NetResponse>, response: Response<NetResponse>) {
                responseCallback.response(200,response.body())
            }

            override fun onFailure(call: Call<NetResponse>, t: Throwable) {
                responseCallback.response(500,null)
            }

        })
    }
}