package com.example.mikeblinkcodetest.chatslist.data.model

import com.google.gson.annotations.SerializedName

data class ChatResponseModel(
    val id: String,
    val name: String,
    @SerializedName("last_updated")
    val lastUpdated: String
)


fun ChatResponseModel.toChatEntity() = ChatEntityModel(id, name, lastUpdated)