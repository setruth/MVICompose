package com.setruth.topicgroup.ui.main.ui

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
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
import com.setruth.topicgroup.ui.main.MainViewModel
import com.setruth.topicgroup.ui.theme.TopicGroupTheme

/**
 * @author  :Setruth
 * time     :2022/9/5 18:34
 * e-mail   :1607908758@qq.com
 * remark   :TopicGroup
 */

@Composable
fun BottomLayout(viewModel: MainViewModel = MainViewModel(), id: Int) {
    Surface(
        color = TopicGroupTheme.colors.box,
        contentColor = TopicGroupTheme.colors.boxText,
        shape =MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.defalut_avatar),
                contentDescription = "头像",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(70.dp)
                    .height(70.dp)
                    .clip(shape = RoundedCornerShape(50))
                    .background(color = TopicGroupTheme.colors.secondary)
                    .border(
                        width = 1.dp,
                        color = TopicGroupTheme.colors.boxText,
                        shape = RoundedCornerShape(50)
                    )
            )
            Spacer(modifier = Modifier
                .height(5.dp)
                .fillMaxWidth())
            Text(text = "组标题", style = MaterialTheme.typography.h1)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "人数")
                Spacer(modifier = Modifier
                    .width(10.dp))
                Text(text = "群号")
            }
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO, name = "light", showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, name = "night", showBackground = true)
@Composable
fun pre() {
    TopicGroupTheme {
        BottomLayout(id = 1)
    }
}