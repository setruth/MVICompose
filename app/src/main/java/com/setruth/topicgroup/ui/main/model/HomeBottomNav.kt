package com.setruth.topicgroup.ui.main.model

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.setruth.topicgroup.R

/**
 * @author  :Setruth
 * time     :2022/9/8 11:54
 * e-mail   :1607908758@qq.com
 * remark   :TopicGroup
 */
object HomeBottomNav {
    val items= listOf(
        ItemPojo(
            label = "消息",
            iconId =  R.drawable.ic_message,
            route = "message",
            checkedIconId = R.drawable.ic_message_fill
        ),
        ItemPojo(
            label = "组群",
            iconId =  R.drawable.ic_participating_groups,
            route = "participatingGroups",
            checkedIconId = R.drawable.ic_participating_groups_fill
        )
    )
    data class ItemPojo(
        val label: String,
        val iconId: Int,
        val checkedIconId: Int,
        val route:String,
    )
}