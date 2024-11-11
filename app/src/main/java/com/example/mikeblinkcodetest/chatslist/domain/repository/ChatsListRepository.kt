package com.example.mikeblinkcodetest.chatslist.domain.repository

import com.example.mikeblinkcodetest.chatslist.domain.model.ChatItem
import kotlinx.coroutines.flow.Flow

interface ChatsListRepository {
    suspend fun getAllChats(): Flow<List<ChatItem>>
}