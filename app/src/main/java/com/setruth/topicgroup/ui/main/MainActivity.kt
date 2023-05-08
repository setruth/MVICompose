package com.setruth.topicgroup.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.compose.rememberNavController
import com.setruth.topicgroup.ui.main.ui.MainLayoutFramework
import com.setruth.topicgroup.ui.theme.TopicGroupTheme

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            TopicGroupTheme {
                window?.statusBarColor = TopicGroupTheme.colors.primary.toArgb()
               MainLayoutFramework()
            }


        }
    }
}


