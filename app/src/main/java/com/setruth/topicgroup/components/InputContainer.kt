package com.setruth.topicgroup.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.setruth.topicgroup.R
import com.setruth.topicgroup.ui.theme.TopicGroupTheme
import java.util.*


/**
 * @author  :Setruth
 * time     :2022/8/20 10:27
 * e-mail   :1607908758@qq.com
 * remark   :TopicGroup
 */


@Composable
fun InputContainer(
    enabled: Boolean = true,
    textChangeEvent: (newText: String) -> Boolean,
    leadingIcon: @Composable (() -> Unit)? = null,
    placeholder: String = "",
    label: String = "",
    modifier: Modifier = Modifier,
    textColor: Color = MaterialTheme.colors.primary,
    activeCleanText: Boolean = false,
    activeTextHide: Boolean = false,
    inputType: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
) {
    var isError by remember { mutableStateOf(false) }
    val text = rememberSaveable { mutableStateOf("") }
    val showState = remember { mutableStateOf(false) }
    val visualTransformation =
        if (!showState.value && activeTextHide) PasswordVisualTransformation() else VisualTransformation.None
    OutlinedTextField(
        enabled = enabled,
        value = text.value,
        onValueChange = { newText: String ->
            isError = textChangeEvent(newText)
            text.value = newText
        },
        leadingIcon = leadingIcon,
        keyboardOptions = inputType,
        label = { Text(text = placeholder) },
        placeholder = { Text(text = label) },
        modifier = modifier,
        singleLine = true,
        maxLines = 1,
        isError = isError,
        visualTransformation = visualTransformation,
        trailingIcon = {
            Row {
                if (activeTextHide) {
                    if (showState.value) {
                        IconButton(onClick = { showState.value = !showState.value }) {
                            Icon(painter = painterResource(id = R.drawable.eye),
                                contentDescription = "显示")
                        }
                    } else {
                        IconButton(onClick = { showState.value = !showState.value }) {
                            Icon(painter = painterResource(id = R.drawable.eyeinvisible),
                                contentDescription = "显示")
                        }
                    }
                }
                if (activeCleanText) {
                    Icon(imageVector = Icons.Default.Clear,
                        contentDescription = "清除",
                        modifier = Modifier
                            .clickable {
                                text.value = ""
                            }
                            .align(Alignment.CenterVertically)
                            .padding(end = 5.dp)
                    )
                }
            }
        }
    )
}

@Composable
fun GetCodeInput(
    textChangeEvent: (newText: String) -> Boolean,
    leadingIcon: @Composable (() -> Unit)? = {
        Icon(imageVector = Icons.Default.Lock,
            contentDescription = null)
    },
    placeholder: String = "验证码",
    label: String = "验证码",
    modifier: Modifier = Modifier,
    inputType: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
    clickSendCode: () -> Unit = {},
    sendBtnColor: Color = TopicGroupTheme.colors.secondary,
    sendBtnTextColor: Color = TopicGroupTheme.colors.primary,
    time: Int = 30,
) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(5.dp)) {
        var countdown by rememberSaveable { mutableStateOf(false) }
        var text by rememberSaveable { mutableStateOf("") }
        var countdownTip by rememberSaveable { mutableStateOf("获取验证码") }
        var isError by rememberSaveable { mutableStateOf(false)}
        TextField(
            isError = isError,
            value = text,
            onValueChange = {
                text = it
                isError=textChangeEvent(it)
            },
            placeholder = {
                Text(text = placeholder)
            },
            label = {
                Text(text = label)
            },
            maxLines = 1,
            singleLine = true,
            leadingIcon = leadingIcon,
            colors = TextFieldDefaults.textFieldColors(
                textColor = TopicGroupTheme.colors.boxText,
                backgroundColor = Color.Transparent
            ),
            modifier = Modifier.weight(1f),
            keyboardOptions = inputType,
        )
        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = sendBtnColor,
                contentColor = sendBtnTextColor
            ),
            onClick = {
                if (!countdown) {
                    clickSendCode()
                    countdown=!countdown
                    timer(time){
                        countdownTip=it.toString()+"秒"
                        if (it==0){
                            countdownTip="获取验证码"
                            countdown=!countdown
                        }
                    }
                }
            },
            modifier = Modifier.padding(start = 5.dp),
            enabled = !countdown
        ) {
            Text(text = countdownTip, modifier = Modifier.padding(0.dp, 5.dp))
        }
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO, name = "白天")
@Composable
fun Preview() {
    TopicGroupTheme {
        GetCodeInput(
            textChangeEvent = {
                false
            },
            placeholder = "登录账号",
            label = "输入邮箱",
        )
    }
}

fun timer(time:Int,timeChange:(t:Int)->Unit){
    var t=time
    Timer().schedule(object : TimerTask() {
        override fun run() {
            t--
            if (t==0){
                Log.e("TAG", "run: ", )
                timeChange(t)
                cancel()
            }else{
                timeChange(t)
            }
        }
    },0, 1000)
}