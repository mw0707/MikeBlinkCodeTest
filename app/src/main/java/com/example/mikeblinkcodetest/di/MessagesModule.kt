package com.example.mikeblinkcodetest.di

import com.example.mikeblinkcodetest.base.database.BlinkCodeTestDatabase
import com.example.mikeblinkcodetest.chat.data.database.MessagesDao
import com.example.mikeblinkcodetest.chat.data.repository.MessagesRepositoryImpl
import com.example.mikeblinkcodetest.chat.domain.repository.MessagesRepository
import com.example.mikeblinkcodetest.chat.domain.usecase.GetMessagesUseCase
import com.example.mikeblinkcodetest.chat.domain.usecase.SendMessageUseCase
import com.example.mikeblinkcodetest.chat.presentation.ChatViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val messagesModule = module {

    single<MessagesDao> {
        val database = get<BlinkCodeTestDatabase>()
        database.messagesDao()
    }

    single<MessagesRepository> {
        MessagesRepositoryImpl(
            database = get(),
            context = androidContext()
        )
    }

    single { GetMessagesUseCase(messagesRepository = get()) }
    single { SendMessageUseCase(messagesRepository = get()) }

    viewModel { ChatViewModel(getMessagesUseCase = get(), sendMessageUseCase = get()) }
}