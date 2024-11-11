package com.example.mikeblinkcodetest.chat.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mikeblinkcodetest.chat.data.model.MessageEntityModel
import kotlinx.coroutines.flow.Flow

@Dao
interface MessagesDao {
    @Query("SELECT * FROM messages WHERE sender_id = :senderId AND receiver_id = :receiverId")
    fun getAllMessagesBySenderAndReceiverIds(
        senderId: String,
        receiverId: String
    ): Flow<List<MessageEntityModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllMessages(messages: List<MessageEntityModel>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNewMessage(messages: MessageEntityModel)
}