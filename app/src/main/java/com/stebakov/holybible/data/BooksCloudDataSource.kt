package com.stebakov.holybible.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.stebakov.holybible.data.net.BookCloud
import com.stebakov.holybible.data.net.BookService

interface BooksCloudDataSource {
    suspend fun fetchBooks(): List<BookCloud>

    class Base(private val service: BookService, private val gson: Gson) : BooksCloudDataSource {
        private val type = object : TypeToken<List<BookCloud>>() {}.type
        override suspend fun fetchBooks(): List<BookCloud> =
            gson.fromJson(service.fetchBooks().string(), type)
    }
}