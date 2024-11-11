package com.example.mikeblinkcodetest.chat.domain.usecase

import com.example.mikeblinkcodetest.base.utils.Constants.MY_ID
import com.example.mikeblinkcodetest.chat.domain.repository.MessagesRepository

class SendMessageUseCase(private val messagesRepository: MessagesRepository) {

    //TODO: Better return state of operation (success or failure)
    suspend operator fun invoke(receiverId: String, message: String) =
        messagesRepository.sendMessage(receiverId, MY_ID, message)
}