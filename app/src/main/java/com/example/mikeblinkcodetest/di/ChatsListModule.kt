package com.example.mikeblinkcodetest.di

import com.example.mikeblinkcodetest.base.database.BlinkCodeTestDatabase
import com.example.mikeblinkcodetest.chatslist.data.database.ChatsListDao
import com.example.mikeblinkcodetest.chatslist.data.repository.ChatsListRepositoryImpl
import com.example.mikeblinkcodetest.chatslist.domain.repository.ChatsListRepository
import com.example.mikeblinkcodetest.chatslist.domain.usecase.GetAllChatsUseCase
import com.example.mikeblinkcodetest.chatslist.presentation.ChatsListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val chatsListModule = module {

    single<ChatsListDao> {
        val database = get<BlinkCodeTestDatabase>()
        database.chatListDao()
    }

    single<ChatsListRepository> {
        ChatsListRepositoryImpl(
            database = get(),
            context = androidContext()
        )
    }

    single { GetAllChatsUseCase(repository = get()) }

    viewModel { ChatsListViewModel(getAllChatsUseCase = get()) }
}