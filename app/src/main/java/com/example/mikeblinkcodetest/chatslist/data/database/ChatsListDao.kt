package com.example.mikeblinkcodetest.chatslist.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mikeblinkcodetest.chatslist.data.model.ChatEntityModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatsListDao {
    @Query("SELECT * FROM chats")
    fun getAllChats(): Flow<List<ChatEntityModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllChats(chats: List<ChatEntityModel>)
}