package com.example.mikeblinkcodetest.chatslist.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mikeblinkcodetest.chatslist.domain.model.ChatItem

@Entity(tableName = "chats")
class ChatEntityModel(
    @PrimaryKey
    val id: String,
    val name: String,
    @ColumnInfo("last_updated")
    val lastUpdated: String
)

fun ChatEntityModel.toDomainModel() = ChatItem(id, name, lastUpdated)