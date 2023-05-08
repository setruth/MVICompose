package com.setruth.topicgroup.ui.main


import androidx.compose.animation.core.tween
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.setruth.topicgroup.ui.main.model.MessageItemInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * @author  :Setruth
 * time     :2022/8/30 14:08
 * e-mail   :1607908758@qq.com
 * remark   :TopicGroup
 */
sealed class MainLayoutEvent{
    object GetMessageList:MainLayoutEvent()
    object AddMessageItem:MainLayoutEvent()
    class MessageUserDetails(val id:Int):MainLayoutEvent()
    class ShowBottomState @OptIn(ExperimentalMaterialApi::class) constructor(val modalBottomSheetState: ModalBottomSheetState) : MainLayoutEvent()
}
sealed class MainLayoutState{
    object MessageLong:MainLayoutState()
}
class MainViewModel : ViewModel() {
    val mainLayoutEventChannel=Channel<MainLayoutEvent>(Channel.UNLIMITED)
    var messageList = mutableStateListOf<MessageItemInfo>()
    var bottomLayoutGroupId by mutableStateOf(-1)
    @OptIn(ExperimentalMaterialApi::class)
    lateinit var state:ModalBottomSheetState
    init {
        handleLayoutEvent()
    }
    @OptIn(ExperimentalMaterialApi::class)
    private fun handleLayoutEvent(){
        viewModelScope.launch {
            mainLayoutEventChannel.consumeAsFlow().collect(){
                when (it) {
                    is MainLayoutEvent.GetMessageList -> getMessageList()
                    is MainLayoutEvent.MessageUserDetails -> getMessageUserDetails(it)
                    is MainLayoutEvent.AddMessageItem -> addItem()
                    is MainLayoutEvent.ShowBottomState -> {
                        it.modalBottomSheetState.animateTo(ModalBottomSheetValue.Hidden, tween(1000))
                    }
                }
            }

        }
    }

    /**
     * TODO 获取当前的消息列表
     *
     */
    private fun getMessageList(){
        viewModelScope.launch{
            messageFlow.flowOn(Dispatchers.Default).collect{
                messageList.add(it)
            }
        }
    }
    private fun addItem(){
        viewModelScope.launch{
            messageList.add(MessageItemInfo(content = "asd"))
        }

    }

    private val messageFlow:Flow<MessageItemInfo> = flow {
        (1..100_00).forEach { _ ->
           emit( MessageItemInfo(content="当前${System.currentTimeMillis()}"))
        }
    }
    /**
     * TODO 获取发送来的消息人信息
     *
     * @param messageUserDetails
     */
    private fun getMessageUserDetails(messageUserDetails: MainLayoutEvent.MessageUserDetails){

    }
}