package com.example.mikeblinkcodetest.chat.domain.model

data class MessageItem(
    val id: String,
    val text: String?,
    val lastUpdated: String,
    val senderId: String,
    val receiverId: String
)
