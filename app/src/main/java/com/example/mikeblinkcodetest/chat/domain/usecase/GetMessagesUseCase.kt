package com.example.mikeblinkcodetest.chat.domain.usecase

import com.example.mikeblinkcodetest.base.utils.Constants
import com.example.mikeblinkcodetest.base.utils.DateUtil
import com.example.mikeblinkcodetest.chat.domain.model.MessageItem
import com.example.mikeblinkcodetest.chat.domain.repository.MessagesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map


class GetMessagesUseCase(private val messagesRepository: MessagesRepository) {

    suspend operator fun invoke(otherId: String): Flow<List<MessageItem>> {

        val receivedMessages =
            messagesRepository.getAllMessagesBySenderAndReceiver(otherId, Constants.MY_ID, true)

        val sentMessages =
            messagesRepository.getAllMessagesBySenderAndReceiver(Constants.MY_ID, otherId, false)

        return sentMessages.combine(receivedMessages) { listSent, listReceived ->
            listSent + listReceived
        }.map { list ->
            list.filter { !it.text.isNullOrEmpty() }
                .sortedBy { DateUtil.parseTimestamp(it.lastUpdated) }
        }
    }
}