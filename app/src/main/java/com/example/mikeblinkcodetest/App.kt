package com.example.mikeblinkcodetest

import android.app.Application
import com.example.mikeblinkcodetest.di.appModule
import com.example.mikeblinkcodetest.di.chatsListModule
import com.example.mikeblinkcodetest.di.messagesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(appModule, chatsListModule, messagesModule)
        }
    }
}