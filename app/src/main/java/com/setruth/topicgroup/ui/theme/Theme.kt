package com.setruth.topicgroup.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color


//白天调色板
private val TopicGroupLightColors = TopicGroupColors(
    buttonText= ButtonText,
    toolBarTitle= ToolBarTitle,
    primary = PrimaryLight,
    secondary = SecondaryLight,
    text = TextLight,
    box = BoxLight,
    boxText = boxTextLight
)

//夜间调色板
private val TopicGroupDarkColors = TopicGroupColors(
    buttonText= ButtonText,
    toolBarTitle= ToolBarTitle,
    primary = PrimaryDark,
    secondary = SecondaryDark,
    text = TextDark,
    box = BoxDark,
    boxText = boxTextDark
)
//md默认配色
private val DarkColorPalette = darkColors(
    primary = TopicGroupDarkColors.primary,
    secondary = TopicGroupDarkColors.secondary
)
//md默认配色
private val LightColorPalette = lightColors(
    primary = TopicGroupLightColors.primary,
    secondary = TopicGroupLightColors.secondary
)
//自定义颜色集
@Stable
class TopicGroupColors(
    buttonText:Color,
    toolBarTitle:Color,
    primary: Color,
    secondary: Color,
    text: Color,
    box: Color,
    boxText: Color,
) {
    var toolBarTitle by mutableStateOf(toolBarTitle)
    var buttonText by mutableStateOf(buttonText)
    var primary by mutableStateOf(primary)
    var secondary by mutableStateOf(secondary)
    var text by mutableStateOf(text)
    var box by mutableStateOf(box)
    var boxText by mutableStateOf(boxText)
    fun copy() = TopicGroupColors(
        buttonText,
        toolBarTitle,
        primary,
        secondary,
        text,
        box,
        boxText
    )

    fun update(other: TopicGroupColors) {
        buttonText=other.buttonText
        toolBarTitle=other.toolBarTitle
        primary = other.primary
        secondary = other.secondary
        text = other.text
        box = other.box
        boxText = other.boxText
    }
}

//颜色提供的入口
@Composable
fun ProvideTopicGroupColors(
    colors: TopicGroupColors,
    content: @Composable () -> Unit,
) {
    val colorPalette = remember {
        colors.copy()
    }
    colorPalette.update(colors)
    CompositionLocalProvider(LocalTopicGroupColors provides colorPalette, content = content)
}

///当前默认色
private val LocalTopicGroupColors = staticCompositionLocalOf<TopicGroupColors> {
    error("没有提供默认值")
}

@Composable
fun TopicGroupTheme(
    sysState:Boolean= isSystemInDarkTheme(),
    content :@Composable () -> Unit
){
    val (default,diy)= if (!sysState) LightColorPalette to TopicGroupLightColors else DarkColorPalette to  TopicGroupDarkColors
    ProvideTopicGroupColors(diy){
        MaterialTheme(
            colors = default,
            shapes = Shapes,
            typography = Typography,
            content = content
        )
    }
}
//提供的自定义的颜色
object TopicGroupTheme {
    val colors: TopicGroupColors
        @Composable
        get() = LocalTopicGroupColors.current
    val padding: Padding=Padding
}

