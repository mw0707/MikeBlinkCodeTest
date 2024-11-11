package com.example.mikeblinkcodetest.chat.data.repository

import android.content.Context
import com.example.mikeblinkcodetest.base.database.BlinkCodeTestDatabase
import com.example.mikeblinkcodetest.base.utils.DateUtil.getCurrentTimestamp
import com.example.mikeblinkcodetest.base.utils.JsonUtil.parseJsonToModel
import com.example.mikeblinkcodetest.base.utils.JsonUtil.readJsonFromAssets
import com.example.mikeblinkcodetest.base.utils.Util.generateRandomString
import com.example.mikeblinkcodetest.chat.data.model.MessageEntityModel
import com.example.mikeblinkcodetest.chat.data.model.MessagesListResponseModel
import com.example.mikeblinkcodetest.chat.data.model.toDomainModel
import com.example.mikeblinkcodetest.chat.data.model.toMessagesEntity
import com.example.mikeblinkcodetest.chat.domain.model.MessageItem
import com.example.mikeblinkcodetest.chat.domain.repository.MessagesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MessagesRepositoryImpl(
    private val database: BlinkCodeTestDatabase,
    private val context: Context
) : MessagesRepository {

    override suspend fun getAllMessagesBySenderAndReceiver(
        senderId: String,
        receiverId: String,
        needToSearchRemotely: Boolean
    ): Flow<List<MessageItem>> {

        /* TODO: Here we need to add network request
       * This logic is about Single Source of Truth (SSOT)
       * As we are creating offline-first app, our Database should be SSOT
       * */

        if (needToSearchRemotely) {
            val response = parseJsonToModel<MessagesListResponseModel>(
                readJsonFromAssets(
                    context,
                    "code_test_data.json"
                )
            )

            val messagesList = response.find { it.personId == senderId }
            messagesList?.let { messagesListNonNull ->
                database.messagesDao()
                    .insertAllMessages(messagesListNonNull.messages.map {
                        it.toMessagesEntity(
                            senderId,
                            receiverId
                        )
                    })
            }
        }


        return database.messagesDao().getAllMessagesBySenderAndReceiverIds(senderId, receiverId)
            .map { databaseMessages -> databaseMessages.map { it.toDomainModel() } }
    }

    override suspend fun sendMessage(senderId: String, receiverId: String, message: String) {
        //TODO: Add API Call
        database.messagesDao().insertNewMessage(
            MessageEntityModel(
                generateRandomString(8),
                message,
                getCurrentTimestamp(),
                receiverId,
                senderId
            )
        )
    }
}