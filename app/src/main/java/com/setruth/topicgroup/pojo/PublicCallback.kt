package com.setruth.topicgroup.pojo

/**
 * @author  :Setruth
 * time     :2022/8/26 9:35
 * e-mail   :1607908758@qq.com
 * remark   :TopicGroup
 */
interface PublicCallback {
    /**
     * TODO 请求响应
     *
     * @param state
     * @param response
     */
    fun response(state:Int,response:Any?){}
}