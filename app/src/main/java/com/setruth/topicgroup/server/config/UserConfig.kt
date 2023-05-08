package com.setruth.topicgroup.server.config

import com.setruth.topicgroup.pojo.NetResponse
import com.setruth.topicgroup.pojo.UserInfo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

/**
 * @author  :Setruth
 * time     :2022/8/26 9:12
 * e-mail   :1607908758@qq.com
 * remark   :TopicGroup
 */

interface UserConfig {
    /**
     * TODO 登录
     *
     * @param loginInfo
     * @return
     */
    @Headers("Content-Type: application/json;charset=utf-8", "Accept: application/json")
    @POST("/user/login")
    fun login(@Body loginInfo:Map<String,String>):Call<NetResponse>

    /**
     * TODO 注册
     *
     * @param loginInfo
     * @return
     */
    @Headers("Content-Type: application/json;charset=utf-8", "Accept: application/json")
    @POST("/user/register")
    fun register(@Body loginInfo:UserInfo):Call<NetResponse>

}