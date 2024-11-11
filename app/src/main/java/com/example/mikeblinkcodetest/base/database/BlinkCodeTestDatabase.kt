package com.example.mikeblinkcodetest.base.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mikeblinkcodetest.chat.data.database.MessagesDao
import com.example.mikeblinkcodetest.chat.data.model.MessageEntityModel
import com.example.mikeblinkcodetest.chatslist.data.database.ChatsListDao
import com.example.mikeblinkcodetest.chatslist.data.model.ChatEntityModel

@Database(entities = [ChatEntityModel::class, MessageEntityModel::class], version = 1)
abstract class BlinkCodeTestDatabase : RoomDatabase() {

    abstract fun chatListDao(): ChatsListDao
    abstract fun messagesDao(): MessagesDao
}