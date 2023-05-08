package com.setruth.topicgroup.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.setruth.topicgroup.R
import com.setruth.topicgroup.ui.main.model.MessageItemInfo
import com.setruth.topicgroup.ui.theme.TopicGroupTheme

/**
 * @author  :Setruth
 * time     :2022/9/4 16:52
 * e-mail   :1607908758@qq.com
 * remark   :TopicGroup
 */

//@Preview(showBackground = true)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MessageItem(
    msgInfo: MessageItemInfo = MessageItemInfo(),
    clickEvent: (groupId: Int) -> Unit = {},
    longClickEvent : (groupId: Int) -> Unit = {},

    ) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp, vertical = 5.dp),
        elevation = 3.dp,

        ) {
        Surface(
            color = TopicGroupTheme.colors.box,
            contentColor = TopicGroupTheme.colors.boxText,
            modifier = Modifier
                .fillMaxWidth()
                .combinedClickable(
                    enabled = true,
                    onClick = {
                        clickEvent(msgInfo.groupId)

                    },
                    onLongClick = {
                        longClickEvent(msgInfo.groupId)
                    }
                )
        ) {
            Row(
                modifier = Modifier.padding(TopicGroupTheme.padding.bottomLayoutPadding)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.defalut_avatar),
                    contentDescription = "头像",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(60.dp)
                        .height(60.dp)
                        .clip(shape = RoundedCornerShape(50))
                        .background(color = TopicGroupTheme.colors.secondary)
                        .border(
                            width = 1.dp,
                            color = TopicGroupTheme.colors.boxText,
                            shape = RoundedCornerShape(50)
                        )
                )
                Column(
                    modifier = Modifier
                        .height(60.dp)
                        .padding(start = 8.dp)
                        .weight(1f),
                    verticalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(text = "组名称")
                    Text(text = "缩略内容")
                }
                Column(
                    modifier = Modifier
                        .height(60.dp)
                        .padding(start = 8.dp),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.End
                ) {
                    Text(text = "时间")
                    msgInfo.contentAmount.takeIf { it > 0 }?.also {
                        Bubble(msgCount = it)
                    }
                }
            }
        }
    }
}

@Composable
fun Bubble(msgCount: Int) {
    val renderBubbleData = if (msgCount < 0) {
        "0"
    } else if (msgCount > 99) {
        "99+"
    } else {
        msgCount.toString()
    }
    Box(modifier = Modifier
        .clip(CircleShape)
        .background(color = TopicGroupTheme.colors.secondary)
    ) {
        Text(
            text = renderBubbleData,
            modifier = Modifier.padding(horizontal = 5.dp),
            color = TopicGroupTheme.colors.buttonText
        )
    }
}

//@Preview(name = "bubbleNIGHT_YES", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
//@Preview(name = "bubbleNIGHT_NO", uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Composable
fun PreviewBubble() {
    TopicGroupTheme {
        Bubble(msgCount = 100)
    }
}

@Preview(name = "ItemNIGHT_YES", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Preview(name = "ItemNIGHT_NO", uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Composable
fun PreItem() {
    TopicGroupTheme {
        MessageItem()
    }
}