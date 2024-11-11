package com.example.mikeblinkcodetest.chat.domain.repository

import com.example.mikeblinkcodetest.chat.domain.model.MessageItem
import kotlinx.coroutines.flow.Flow

interface MessagesRepository {
    suspend fun getAllMessagesBySenderAndReceiver(
        senderId: String,
        receiverId: String,
        needToSearchRemotely: Boolean
    ): Flow<List<MessageItem>>

    suspend fun sendMessage(
        senderId: String,
        receiverId: String,
        message: String
    )
}