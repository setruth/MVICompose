package com.setruth.topicgroup.ui.main.ui

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.setruth.topicgroup.R
import com.setruth.topicgroup.ui.main.model.SideMenuItemType
import com.setruth.topicgroup.ui.theme.TopicGroupStyle
import com.setruth.topicgroup.ui.theme.TopicGroupTheme

/**
 * @author  :Setruth
 * time     :2022/9/6 15:13
 * e-mail   :1607908758@qq.com
 * remark   :TopicGroup
 */
/**
 * TODO 主页的侧边菜单
 *
 */
@Composable
fun SideMenuLayout(
    itemClickEvent: (type: SideMenuItemType) -> Unit,
) {
    Column(modifier = Modifier
        .fillMaxSize()) {
        Header()
        SideMenuItemsLayout()
    }


}

@Composable
fun Header() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        contentColor = TopicGroupTheme.colors.buttonText,
        color = TopicGroupTheme.colors.primary
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 25.dp),
            verticalAlignment = Alignment.CenterVertically
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
            Spacer(modifier = Modifier.width(15.dp))
            Column(modifier = Modifier
                .height(60.dp)
                .weight(1f), verticalArrangement = Arrangement.SpaceBetween) {
                Text(text = "昵称:")
                Text(text = "id:")
            }
            Icon(
                imageVector = Icons.Default.Build,
                contentDescription = "UI模式切换",
                modifier = Modifier
                    .size(40.dp)
                    .clickable {

                    }
            )
        }
    }
}

@Composable
fun SideMenuItemsLayout() {
    Surface(
        color = TopicGroupTheme.colors.box,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            SideMenuItem(itemText = "退出登录", icon = Icons.Default.ExitToApp)
        }
    }
}

@Composable
fun SideMenuItem(
    itemType: SideMenuItemType = SideMenuItemType.NULL,
    icon: ImageVector = Icons.Default.Build,
    itemText: String = "",
    itemClickEvent: (type: SideMenuItemType) -> Unit = {},
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                itemClickEvent(itemType)
            },
        color=TopicGroupTheme.colors.box,
        contentColor = TopicGroupTheme.colors.boxText
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = icon, contentDescription = "每一个点击项图标",modifier = Modifier.size(25.dp))
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = itemText,style= TopicGroupStyle.side_menu )
        }
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES, name = "night")
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO, name = "nightNO")
@Composable
fun Pre() {
    TopicGroupTheme {
        SideMenuLayout {}
    }
}