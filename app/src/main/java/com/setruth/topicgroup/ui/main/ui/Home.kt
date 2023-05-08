package com.setruth.topicgroup.ui.main.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.setruth.topicgroup.ui.theme.TopicGroupTheme
import kotlinx.coroutines.launch

/**
 * @author  :Setruth
 * time     :2022/9/8 17:26
 * e-mail   :1607908758@qq.com
 * remark   :TopicGroup
 */

/**
 * TODO 主页
 *
 */
@Composable
fun Home(
    appMainNavController: NavHostController
) {
    val scaffoldState = rememberScaffoldState(drawerState = DrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()
    val bottomNavController = rememberNavController()
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = TopicGroupTheme.colors.primary,
                contentColor = TopicGroupTheme.colors.buttonText,
                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 5.dp),
                elevation = 10.dp
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "话题组",
                        style = MaterialTheme.typography.h1,
                        color = TopicGroupTheme.colors.buttonText)
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "菜单",
                        modifier = Modifier
                            .size(40.dp)
                            .clickable {
                                scope.launch {
                                    scaffoldState.drawerState.open()
                                }
                            },
                        tint = TopicGroupTheme.colors.buttonText
                    )
                }
            }
        },
        drawerElevation = 10.dp,
        drawerGesturesEnabled = true,
        scaffoldState = scaffoldState,
        drawerContent = {
            SideMenuLayout {
            }
        },
        bottomBar = {
            BottomNavigationLayout(bottomNavController)
        },
        content = {
            HomeLayout(appMainNavController = appMainNavController,bottomNavController =bottomNavController)
        }
    )
}