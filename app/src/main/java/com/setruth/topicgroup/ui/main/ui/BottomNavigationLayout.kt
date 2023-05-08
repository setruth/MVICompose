package com.setruth.topicgroup.ui.main.ui

import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.setruth.topicgroup.ui.main.model.HomeBottomNav
import com.setruth.topicgroup.ui.theme.NavTextNoClick
import com.setruth.topicgroup.ui.theme.TopicGroupTheme

/**
 * @author  :Setruth
 * time     :2022/9/8 11:51
 * e-mail   :1607908758@qq.com
 * remark   :TopicGroup
 */

@Composable
fun BottomNavigationLayout(navController: NavController) {
    BottomNavigation (
        backgroundColor = TopicGroupTheme.colors.primary
            ){
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        HomeBottomNav.items.forEach {
            BottomNavigationItem(
                selected = currentRoute == it.route,
                onClick = {
                    navController.navigate(it.route)
                },
                icon = {
                    if (currentRoute == it.route) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = it.checkedIconId),
                            contentDescription = it.label,
                            modifier=Modifier.size(30.dp),
                            tint = TopicGroupTheme.colors.buttonText
                        )
                    }else{
                        Icon(
                            imageVector = ImageVector.vectorResource(id = it.iconId),
                            contentDescription = it.label,
                            modifier=Modifier.size(30.dp),
                            tint = NavTextNoClick
                        )
                    }

                },
                label = {
                    if (currentRoute == it.route) {
                        Text(text = it.label, color = TopicGroupTheme.colors.buttonText)
                    }else{
                        Text(text = it.label, color = NavTextNoClick)
                    }
                }
            )
        }
    }
}