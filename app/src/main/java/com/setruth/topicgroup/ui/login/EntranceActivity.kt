package com.setruth.topicgroup.ui.login

import android.content.Intent
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.setruth.topicgroup.components.GetCodeInput
import com.setruth.topicgroup.components.InputContainer
import com.setruth.topicgroup.components.LoadingButton
import com.setruth.topicgroup.pojo.NetResponse
import com.setruth.topicgroup.pojo.PublicCallback
import com.setruth.topicgroup.pojo.UserInfo
import com.setruth.topicgroup.server.RequestBuilder
import com.setruth.topicgroup.server.api.UserApi
import com.setruth.topicgroup.server.config.UserConfig
import com.setruth.topicgroup.ui.main.MainActivity
import com.setruth.topicgroup.ui.theme.TopicGroupTheme
import kotlinx.coroutines.launch

class EntranceActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TopicGroupTheme {
                window?.statusBarColor = TopicGroupTheme.colors.primary.toArgb()
                EntranceLayout()
            }
        }
    }

    @Preview(uiMode = UI_MODE_NIGHT_YES, name = "夜晚")
    @Preview(uiMode = UI_MODE_NIGHT_NO, name = "白天")
    /**
     * TODO 入口的主要页面架构
     *
     */
    @Composable
    @ExperimentalMaterialApi
    fun EntranceLayout() {
        var nowBottomState by rememberSaveable {
            mutableStateOf(1)
        }
        val sheetState =
            rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
        val scope = rememberCoroutineScope()
        Scaffold(
            topBar = {
                TopAppBar(
                    backgroundColor = TopicGroupTheme.colors.primary,
                    title = {
                        Text(text = "话题组", color = TopicGroupTheme.colors.toolBarTitle)
                    },
                    elevation = 10.dp
                )
            }
        ) {
            ModalBottomSheetLayout(
                sheetContent = {
                    if (nowBottomState == RETRIEVE_PASSWORD_STATE) {
                        RetrievePasswordLayout()
                    } else {
                        RegisterLayout()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                sheetState = sheetState,
                sheetShape = RoundedCornerShape(10.dp),
                sheetElevation = 20.dp,
                sheetBackgroundColor = TopicGroupTheme.colors.box,
                sheetContentColor = TopicGroupTheme.colors.boxText,
                scrimColor = Color.Transparent,
            ) {
                EntranceContent {
                    nowBottomState = it
                    scope.launch {
                        if (sheetState.isVisible)
                            sheetState.animateTo(ModalBottomSheetValue.Hidden, tween(1000))
                        else
                            sheetState.animateTo(ModalBottomSheetValue.Expanded, tween(400))
                    }
                }
            }
        }

    }

    /**
     * TODO 软件入口页面
     *
     */
    @Composable
    fun EntranceContent(changeBottomState: (clickType: Int) -> Unit) {

        Surface(Modifier.fillMaxSize()) {
            Box(
                Modifier
                    .fillMaxSize()
                    .background(TopicGroupTheme.colors.box)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.align(Alignment.Center)) {

                    Text(
                        text = "欢迎来到话题组",
                        color = TopicGroupTheme.colors.boxText,
                        style = MaterialTheme.typography.h4,
                        modifier = Modifier.padding(start = 30.dp, end = 30.dp)
                    )
                    Text(
                        text = "每个成员,都是独一无二的",
                        color = TopicGroupTheme.colors.boxText,
                        style = MaterialTheme.typography.h4,
                        modifier = Modifier.padding(bottom = 20.dp, start = 30.dp, end = 30.dp)
                    )
                    Card(elevation = 10.dp,
                        backgroundColor = TopicGroupTheme.colors.box,
                        contentColor = TopicGroupTheme.colors.boxText,
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.padding(35.dp, 0.dp)
                    ) {
                        LoginLayout(changeBottomState)
                    }
                }
            }
        }

    }

    /**
     * TODO 登录页面
     *
     * @param changeBottomState 点击注册时弹出底部窗口
     */
    @Composable
    fun LoginLayout(changeBottomState: (clickType: Int) -> Unit) {
        var account by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        Column(
            modifier = Modifier.padding(15.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "登录",
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .padding(bottom = 10.dp),
                style = MaterialTheme.typography.h1,
            )
            InputContainer(
                textChangeEvent = {
                    account = it
                    it == ""
                },
                inputType = KeyboardOptions(keyboardType = KeyboardType.Email),
                placeholder = "账户",
                label = "输入注册邮箱",
                modifier = Modifier.fillMaxWidth(),
                activeCleanText = true,
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Email, contentDescription = "passwordIcon")
                }
            )
            InputContainer(
                textChangeEvent = {
                    password = it
                    it == ""
                },
                placeholder = "密码",
                label = "输入登录密码",
                modifier = Modifier.fillMaxWidth(),
                activeCleanText = true,
                activeTextHide = true,
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "passwordIcon")
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            LoadingButton(
                buttonColor = TopicGroupTheme.colors.secondary,
                loading = TopicGroupTheme.colors.buttonText,
                buttonTextColor = TopicGroupTheme.colors.buttonText,
                clickCallback = { buttonText, isLoading ->
                    if (account == "" && password == "") {
                        Toast.makeText(this@EntranceActivity.applicationContext,
                            "账号或则密码不能为空",
                            Toast.LENGTH_SHORT).show()
                    } else {
                        //TODO 直接进入
                        Intent(this@EntranceActivity.applicationContext,
                            MainActivity::class.java).apply {
                            startActivity(this)
                            this@EntranceActivity.finish()
                        }
                        //
                        isLoading.value = true
                        buttonText.value = "正在加载"
                        with(UserApi(RequestBuilder(this@EntranceActivity.applicationContext,
                            UserConfig::class.java))) {
                            loginAPI(mapOf("account" to account, "password" to password),
                                object : PublicCallback {
                                    override fun response(state: Int, response: Any?) {
                                        if (state == 200 && response != null) {
                                            (response as NetResponse).apply {
                                                if (this.state == 200) {
                                                    isLoading.value = false
                                                    Intent(this@EntranceActivity.applicationContext,
                                                        MainActivity::class.java).apply {
                                                        startActivity(this)
                                                        this@EntranceActivity.finish()
                                                    }
                                                } else {
                                                    isLoading.value = false
                                                    Toast.makeText(
                                                        this@EntranceActivity.applicationContext,
                                                        message,
                                                        Toast.LENGTH_SHORT
                                                    ).show()
                                                }
                                            }
                                        } else {
                                            isLoading.value = false
                                            Toast.makeText(this@EntranceActivity.applicationContext,
                                                "连接超时",
                                                Toast.LENGTH_SHORT).show()
                                        }
                                        buttonText.value = "登录"
                                    }
                                })
                        }
                    }

                }
            )
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween) {
                TextButton(onClick = { changeBottomState(RETRIEVE_PASSWORD_STATE) }) {
                    Text(text = "重置密码")
                }
                TextButton(onClick = { changeBottomState(REGISTER_LAYOUT_STATE) }) {
                    Text(text = "注册")
                }
            }
        }

    }

    /**
     * TODO 注册页面
     *
     */
    @Composable
    fun RegisterLayout() {
        var account by rememberSaveable { mutableStateOf("") }
        var nickName by rememberSaveable { mutableStateOf("") }
        var password by rememberSaveable { mutableStateOf("") }
        var confirmPassword by rememberSaveable { mutableStateOf("") }
        var sex by rememberSaveable { mutableStateOf(-1) }
        Column(
            modifier = Modifier
                .padding(30.dp, 10.dp)
                .fillMaxWidth()
                .background(TopicGroupTheme.colors.box),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "注册",
                modifier = Modifier
                    .align(alignment = Alignment.Start),
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.primary
            )
            Spacer(modifier = Modifier.height(10.dp))
            InputContainer(
                textChangeEvent = {
                    account = it
                    it == ""
                },
                inputType = KeyboardOptions(keyboardType = KeyboardType.Email),
                placeholder = "账户",
                label = "输入注册邮箱",
                modifier = Modifier.fillMaxWidth(),
                activeCleanText = true,
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Email, contentDescription = "passwordIcon")
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            InputContainer(
                textChangeEvent = {
                    nickName = it
                    it == ""
                },
                placeholder = "昵称",
                label = "输入昵称",
                modifier = Modifier.fillMaxWidth(),
                activeCleanText = true,
                leadingIcon = {
                    Icon(imageVector = Icons.Default.AccountBox,
                        contentDescription = "passwordIcon")
                }
            )


            Spacer(modifier = Modifier.height(10.dp))
            InputContainer(
                textChangeEvent = {
                    password = it
                    it == ""
                },
                placeholder = "密码",
                label = "6~18位",
                activeTextHide = true,
                modifier = Modifier.fillMaxWidth(),
                activeCleanText = true,
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "passwordIcon")
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            InputContainer(
                textChangeEvent = {
                    confirmPassword = it
                    confirmPassword == "" || confirmPassword != password
                },
                placeholder = "再次输入密码",
                label = "确认密码",
                activeTextHide = true,
                modifier = Modifier.fillMaxWidth(),
                activeCleanText = true,
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "passwordIcon")
                }
            )

            Spacer(modifier = Modifier.height(10.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "性别:")
                Spacer(modifier = Modifier.width(5.dp))
                RadioButton(
                    selected = sex == -1,
                    onClick = {
                        sex = -1
                    }
                )
                Text(text = "保密")
                RadioButton(
                    selected = sex == 1,
                    onClick = {
                        sex = 1
                    }
                )
                Text(text = "男")
                RadioButton(
                    selected = sex == 0,
                    onClick = {
                        sex = -0
                    }
                )
                Text(text = "女")
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "兴趣爱好：")
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "乒乓球")
                    Checkbox(checked = true, onCheckedChange ={} )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "篮球")
                    Checkbox(checked = false, onCheckedChange ={} )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "羽毛球")
                    Checkbox(checked = true, onCheckedChange ={} )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))

            LoadingButton(
                buttonText = "注册",
                buttonColor = TopicGroupTheme.colors.secondary,
                loading = TopicGroupTheme.colors.buttonText,
                buttonTextColor = TopicGroupTheme.colors.buttonText,
                clickCallback = { buttonText, isLoading ->
                    if (password == confirmPassword && account != "" && password != "" && nickName != "") {
                        buttonText.value = "注册中"
                        isLoading.value = true
                        with(UserApi(RequestBuilder(this@EntranceActivity.applicationContext,
                            UserConfig::class.java))) {
                            registerAPI(UserInfo(
                                account = account,
                                password = password,
                                sex = sex,
                                nickName = nickName
                            ), object : PublicCallback {
                                override fun response(state: Int, response: Any?) {
                                    if (state == 200 && response != null) {
                                        (response as NetResponse).apply {
                                            if (this.state == 200) {
                                                buttonText.value = "注册"
                                                isLoading.value = false
                                                Toast.makeText(this@EntranceActivity.applicationContext,
                                                    "注册成功稍后重试",
                                                    Toast.LENGTH_SHORT).show()
                                            } else {
                                                buttonText.value = "注册"
                                                isLoading.value = false
                                                Toast.makeText(this@EntranceActivity.applicationContext,
                                                    this.message,
                                                    Toast.LENGTH_SHORT).show()
                                            }
                                        }
                                    } else {
                                        buttonText.value = "注册"
                                        isLoading.value = true
                                        Toast.makeText(this@EntranceActivity.applicationContext,
                                            "注册超时,稍后重试",
                                            Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }
                            )
                        }
                    } else {
                        Toast.makeText(this@EntranceActivity.applicationContext,
                            "你输入的信息有误",
                            Toast.LENGTH_SHORT).show()
                    }
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
    }

    /**
     * TODO 找回密码页面
     *
     */
    @Composable
    fun RetrievePasswordLayout() {
        var pwdInput by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .padding(30.dp, 10.dp)

                .background(TopicGroupTheme.colors.box),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "重置密码",
                modifier = Modifier
                    .align(alignment = Alignment.Start),
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.primary
            )
            Spacer(modifier = Modifier.height(10.dp))
            InputContainer(
                textChangeEvent = { it ->
                    it.takeIf { it != "" }
                        ?.let {
                            pwdInput = true
                            false
                        }
                        ?: let {
                            pwdInput = false
                            true
                        }
                },
                placeholder = "验证邮箱",
                label = "输入注册邮箱",
                modifier = Modifier.fillMaxWidth(),
                activeCleanText = true,
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Email, contentDescription = "passwordIcon")
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            GetCodeInput(
                textChangeEvent = {
                    if (it == "") {
                        pwdInput = false
                        true
                    } else {
                        pwdInput = true
                        false
                    }
                },
                sendBtnColor = TopicGroupTheme.colors.secondary,
                sendBtnTextColor = TopicGroupTheme.colors.buttonText
            )
            Spacer(modifier = Modifier.height(10.dp))

            InputContainer(
                enabled = pwdInput,
                textChangeEvent = { it ->
                    false
                },
                placeholder = "新密码",
                label = "输入您新的密码",
                activeTextHide = true,
                modifier = Modifier.fillMaxWidth(),
                activeCleanText = true,
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "passwordIcon")
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            InputContainer(
                enabled = pwdInput,
                textChangeEvent = { it ->
                    false
                },
                placeholder = "确认密码",
                label = "再次输入新密码",
                activeTextHide = true,
                modifier = Modifier.fillMaxWidth(),
                activeCleanText = true,
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "passwordIcon")
                }
            )

            Spacer(modifier = Modifier.height(10.dp))
            Button(
                enabled = pwdInput,
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = TopicGroupTheme.colors.secondary,
                    contentColor = TopicGroupTheme.colors.buttonText
                )
            ) {
                Column() {
                    Text(text = "确认重置", style = MaterialTheme.typography.h4)
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }

    private companion object {
        const val REGISTER_LAYOUT_STATE = 1
        const val RETRIEVE_PASSWORD_STATE = 2
    }
}