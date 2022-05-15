package com.stebakov.holybible.data.chapters.cloud

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

interface ChaptersCloudDataSource {

    suspend fun fetchChapters(bookId: Int): List<ChaptersCloud>

    class Base(
        private val service: ChaptersService,
        private val gson: Gson
    ) : ChaptersCloudDataSource {

        override suspend fun fetchChapters(bookId: Int): List<ChaptersCloud> =
            gson.fromJson(
                service.fetchChapters(bookId).string(),
                object : TypeToken<List<ChaptersCloud>>() {}.type
            )
    }
}