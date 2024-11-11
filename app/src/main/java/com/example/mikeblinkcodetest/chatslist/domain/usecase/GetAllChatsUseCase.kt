package com.example.mikeblinkcodetest.chatslist.domain.usecase

import com.example.mikeblinkcodetest.base.utils.DateUtil
import com.example.mikeblinkcodetest.chatslist.domain.model.ChatItem
import com.example.mikeblinkcodetest.chatslist.domain.repository.ChatsListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAllChatsUseCase(private val repository: ChatsListRepository) {
    suspend operator fun invoke(sortBy: SortingType): Flow<List<ChatItem>> {
        val chatsFlow = repository.getAllChats()

        return when (sortBy) {
            SortingType.DATE_DESC -> chatsFlow.map { chat ->
                chat.sortedByDescending { DateUtil.parseTimestamp(it.lastUpdated) }
            }

            SortingType.DEFAULT -> chatsFlow
        }
    }
}

enum class SortingType {
    DEFAULT, DATE_DESC
}