package com.example.mikeblinkcodetest.di

import androidx.room.Room
import com.example.mikeblinkcodetest.base.database.BlinkCodeTestDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single {
        Room.databaseBuilder(androidContext(), BlinkCodeTestDatabase::class.java, "database.db")
            .build()
    }
}