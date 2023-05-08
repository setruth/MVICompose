package com.setruth.topicgroup.ui.main.ui

import androidx.compose.animation.core.tween
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.setruth.topicgroup.components.MessageItem
import com.setruth.topicgroup.ui.main.model.MessageItemInfo
import kotlinx.coroutines.launch

/**
 * @author  :Setruth
 * time     :2022/9/7 23:56
 * e-mail   :1607908758@qq.com
 * remark   :TopicGroup
 */

/**
 * TODO 主要的页面内容
 *
 */
@Composable
fun HomeLayout(
    appMainNavController: NavHostController,
    bottomNavController: NavHostController,
) {
    NavHost(
        navController = bottomNavController,
        startDestination = "message",
        builder = {
            composable("message") {
                MessageModelLayout(appMainNavController)
            }
            composable("participatingGroups") {
                ParticipatingGroupsLayout()
            }
        }
    )
}

/**
 * TODO 消息列表模块
 *
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MessageModelLayout(appMainNavController: NavHostController) {
    val scope= rememberCoroutineScope()
    val modalBottomSheetState= rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    ModalBottomSheetLayout(
        sheetContent = {
            BottomLayout(id = 1)
        },
        sheetState = modalBottomSheetState
    ) {
        val toList = (0..100).toList()
        val context=LocalContext.current
        LazyColumn(content = {
            items(toList) {
                MessageItem(
                    longClickEvent = {
                        scope.launch {
                            modalBottomSheetState.animateTo(ModalBottomSheetValue.Expanded, tween(500))
                        }
                    },
                    msgInfo= MessageItemInfo(
                        contentAmount = it
                    ),
                    clickEvent = {
                        appMainNavController.navigate("chat")
                    }
                )
            }
        })
    }

}

/**
 * TODO 参与的组模块
 *
 */
@Composable
fun ParticipatingGroupsLayout() {
    Text(text = "participatingGroupsLayout")

}