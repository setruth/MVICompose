package com.setruth.topicgroup.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * @author  :Setruth
 * time     :2022/8/26 9:54
 * e-mail   :1607908758@qq.com
 * remark   :TopicGroup
 */

@Composable
fun LoadingButton(
    buttonText: String="登录",
    buttonColor: Color =MaterialTheme.colors.secondary,
    buttonTextColor: Color =MaterialTheme.colors.primary,
    loading: Color =MaterialTheme.colors.primary,
    buttonRadius:Dp=10.dp,
    clickCallback:(buttonText: MutableState<String>,isLoading: MutableState<Boolean>)->Unit={_,_->}
) {
    var text =remember { mutableStateOf(buttonText) }
    var isLoading = remember { mutableStateOf(false) }
    Button(
        onClick = {
            clickCallback(text,isLoading)
        },
        enabled =!isLoading.value,
        colors = ButtonDefaults.buttonColors(
            backgroundColor=buttonColor,
            contentColor =buttonTextColor
        ),
        shape = RoundedCornerShape(buttonRadius)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isLoading.value) {
                CircularProgressIndicator(
                    color = loading,
                    modifier = Modifier.padding(end = 10.dp)
                )
            }
            Text(text = text.value, style = MaterialTheme.typography.h4)


        }
    }
}
@Preview(showBackground = true)
@Composable
fun preview(){
    LoadingButton()
}