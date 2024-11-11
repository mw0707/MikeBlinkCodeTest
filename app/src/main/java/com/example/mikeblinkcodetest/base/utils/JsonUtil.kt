package com.example.mikeblinkcodetest.base.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object JsonUtil {

    fun readJsonFromAssets(context: Context, fileName: String): String {
        return context.assets.open(fileName).bufferedReader().use { it.readText() }
    }

    inline fun <reified T> parseJsonToModel(jsonString: String): List<T> {
        val gson = Gson()
        return gson.fromJson(jsonString, object : TypeToken<List<T>>() {}.type)
    }
}