package com.setruth.topicgroup.ui.main.ui

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.setruth.topicgroup.ui.main.MainViewModel
import com.setruth.topicgroup.ui.theme.TopicGroupTheme
import kotlinx.coroutines.launch

/**
 * @author  :Setruth
 * time     :2022/8/30 11:31
 * e-mail   :1607908758@qq.com
 * remark   :TopicGroup
 */


@Composable
fun MainLayoutFramework(
    viewModel: MainViewModel = MainViewModel(),
){
    //获取导航控制器
    val appMainNavController = rememberNavController()
    //获取当前的nav状态
    val backstackEntry = appMainNavController.currentBackStackEntryAsState()
    //获取当前路由信息
    val route = backstackEntry.value?.destination?.route
    NavHost(navController = appMainNavController, startDestination = "home"){
        composable("home"){
            Home(appMainNavController)
        }
        composable("chat"){

        }
    }
}

@Preview(name = "night", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Preview(name = "light", uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Composable
fun PreviewMainLayoutFramework() {
    TopicGroupTheme {
        MainLayoutFramework()
    }
}