package com.stebakov.holybible.data

import com.stebakov.holybible.data.net.BookCloud
import com.stebakov.holybible.data.net.BookService

interface BooksCloudDataSource {
    suspend fun fetchBooks(): List<BookCloud>

    class Base(private val service: BookService) : BooksCloudDataSource{
        override suspend fun fetchBooks(): List<BookCloud> {
            return service.fetchBooks()
        }

    }
}