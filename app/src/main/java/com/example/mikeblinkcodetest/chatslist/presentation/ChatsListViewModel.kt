package com.example.mikeblinkcodetest.chatslist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mikeblinkcodetest.chatslist.domain.model.ChatItem
import com.example.mikeblinkcodetest.chatslist.domain.usecase.GetAllChatsUseCase
import com.example.mikeblinkcodetest.chatslist.domain.usecase.SortingType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChatsListViewModel(private val getAllChatsUseCase: GetAllChatsUseCase) : ViewModel() {

    private val _stateFlow = MutableStateFlow<ScreenState>(ScreenState.Loading)
    val stateFlow = _stateFlow.asStateFlow()

    fun fetchState() {
        viewModelScope.launch {
            getAllChatsUseCase.invoke(SortingType.DATE_DESC)
                .collect { data -> _stateFlow.update { ScreenState.Chats(data) } }
        }
    }

    fun onChatClicked(chatItem: ChatItem) {
        _stateFlow.update {
            ScreenState.DetailedChat(chatItem.id)
        }
    }
}

sealed class ScreenState {
    data object Loading : ScreenState()
    data object Error : ScreenState() //TODO: To be done if there is more than 3 hours
    data class Chats(val items: List<ChatItem>) : ScreenState()
    data class DetailedChat(val chatId: String) : ScreenState()
}