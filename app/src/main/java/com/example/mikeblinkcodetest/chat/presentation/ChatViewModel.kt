package com.example.mikeblinkcodetest.chat.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mikeblinkcodetest.chat.domain.model.MessageItem
import com.example.mikeblinkcodetest.chat.domain.usecase.GetMessagesUseCase
import com.example.mikeblinkcodetest.chat.domain.usecase.SendMessageUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChatViewModel(
    private val getMessagesUseCase: GetMessagesUseCase,
    private val sendMessageUseCase: SendMessageUseCase
) : ViewModel() {

    private val _stateFlow = MutableStateFlow<ScreenState>(ScreenState.Loading)
    val stateFlow = _stateFlow.asStateFlow()

    fun fetchState(otherId: String) {
        viewModelScope.launch {
            getMessagesUseCase.invoke(otherId)
                .collect { data -> _stateFlow.update { ScreenState.Messages(data) } }
        }
    }

    fun dispatch(event: ScreenEvent) {
        when (event) {
            is ScreenEvent.SendMessage -> {
                viewModelScope.launch {
                    sendMessageUseCase.invoke(event.receiverId, event.message)
                }
            }

            else -> throw IllegalStateException("Invalid event")
        }
    }

    sealed class ScreenState {
        data object Loading : ScreenState()
        data object Error : ScreenState() //TODO: To be done if there is more than 3 hours
        data class Messages(val items: List<MessageItem>) : ScreenState()
    }

    sealed class ScreenEvent {
        data class SendMessage(val receiverId: String, val message: String) : ScreenEvent()
    }
}