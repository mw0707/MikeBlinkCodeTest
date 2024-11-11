package com.example.mikeblinkcodetest.chat.data.model

import com.google.gson.annotations.SerializedName

data class MessagesListResponseModel(
    @SerializedName("id")
    val personId: String,
    val messages: List<MessagesResponseModel>
)
