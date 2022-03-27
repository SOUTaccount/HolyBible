package com.stebakov.holybible.data

import com.stebakov.holybible.data.net.BookServerModel
import com.stebakov.holybible.data.net.BookService

interface BooksCloudDataSource {
    suspend fun fetchBooks(): List<BookServerModel>

    class Base(private val service: BookService) : BooksCloudDataSource{
        override suspend fun fetchBooks(): List<BookServerModel> {
            return service.fetchBooks()
        }

    }
}