package com.example.mikeblinkcodetest.chat.data.model

import com.google.gson.annotations.SerializedName

class MessagesResponseModel(
    val id: String,
    val text: String,
    @SerializedName("last_updated")
    val lastUpdated: String
)

fun MessagesResponseModel.toMessagesEntity(senderId: String, receiverId: String) =
    MessageEntityModel(id, text, lastUpdated, senderId, receiverId)