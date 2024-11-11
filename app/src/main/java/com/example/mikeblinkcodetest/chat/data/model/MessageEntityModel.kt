package com.example.mikeblinkcodetest.chat.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mikeblinkcodetest.chat.domain.model.MessageItem

@Entity(tableName = "messages")
class MessageEntityModel(
    @PrimaryKey
    val id: String,
    val text: String?,
    @ColumnInfo("last_updated")
    val lastUpdated: String,
    @ColumnInfo("sender_id")
    val senderId: String,
    @ColumnInfo("receiver_id")
    val receiverId: String
)

fun MessageEntityModel.toDomainModel() = MessageItem(id, text, lastUpdated, senderId, receiverId)
