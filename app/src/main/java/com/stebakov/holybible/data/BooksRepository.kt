package com.stebakov.holybible.data

import java.lang.Exception

interface BooksRepository {
    suspend fun fetchBooks(): BookData

    class Base(private val booksCloudDataSource: BooksCloudDataSource) : BooksRepository {
        override suspend fun fetchBooks() = try {
            BookData.Success(booksCloudDataSource.fetchBooks())
        } catch (e: Exception) {
            BookData.Fail(e)
        }

    }
}