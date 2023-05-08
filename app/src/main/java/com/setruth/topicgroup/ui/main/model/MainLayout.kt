package com.setruth.topicgroup.ui.main.model

/**
 * @author  :Setruth
 * time     :2022/8/30 14:09
 * e-mail   :1607908758@qq.com
 * remark   :TopicGroup
 */



data class MainLayout(
    val id:Int=-1,
    val account:String,
    val nickName:String,
)

/**
 * TODO 消息列表的每一项内容结构
 *
 * @property sendName
 * @property time
 * @property content
 * @property messageType
 * @property avatar
 */
data class MessageItemInfo(
    val groupId:Int=-1,
    val groupName:String="",
    val time:Long=0,
    val content:String="",
    val messageType:MessageType=MessageType.TEXT,
    val avatar:String="",
    val contentAmount:Int=0,
)

/**
 * TODO 消息的类型
 *
 */
enum class MessageType{
    TEXT,
    IMAGE,
    LINK
}


