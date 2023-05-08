package com.setruth.topicgroup.pojo

/**
 * @author  :Setruth
 * time     :2022/8/28 13:27
 * e-mail   :1607908758@qq.com
 * remark   :TopicGroup
 */

data class UserInfo(
    val id:Int=-1,
    val account:String="",
    val password:String="",
    val sex:Int=0,
    val nickName:String=""
)
