package com.example.mikeblinkcodetest.chatslist.data.repository

import android.content.Context
import com.example.mikeblinkcodetest.base.database.BlinkCodeTestDatabase
import com.example.mikeblinkcodetest.base.utils.JsonUtil.parseJsonToModel
import com.example.mikeblinkcodetest.base.utils.JsonUtil.readJsonFromAssets
import com.example.mikeblinkcodetest.chatslist.data.model.ChatResponseModel
import com.example.mikeblinkcodetest.chatslist.data.model.toChatEntity
import com.example.mikeblinkcodetest.chatslist.data.model.toDomainModel
import com.example.mikeblinkcodetest.chatslist.domain.model.ChatItem
import com.example.mikeblinkcodetest.chatslist.domain.repository.ChatsListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ChatsListRepositoryImpl(
    private val database: BlinkCodeTestDatabase,
    private val context: Context
) : ChatsListRepository {

    override suspend fun getAllChats(): Flow<List<ChatItem>> {

        /* TODO: Here we need to add network request
        * This logic is about Single Source of Truth (SSOT)
        * As we are creating offline-first app, our Database should be SSOT
        * */
        val response = parseJsonToModel<ChatResponseModel>(
            readJsonFromAssets(
                context,
                "code_test_data.json"
            )
        )

        database.chatListDao().insertAllChats(response.map { it.toChatEntity() })

        return database.chatListDao().getAllChats()
            .map { databaseChats -> databaseChats.map { it.toDomainModel() } }
    }
}