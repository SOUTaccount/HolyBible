package com.stebakov.holybible.data

import com.stebakov.holybible.data.cache.BooksCacheDataSource
import com.stebakov.holybible.data.cache.BooksCacheMapper
import java.lang.Exception

interface BooksRepository {
    suspend fun fetchBooks(): BooksData

    class Base(
        private val booksCloudDataSource: BooksCloudDataSource,
        private val cacheDataSource: BooksCacheDataSource,
        private val booksCloudMapper: BooksCloudMapper,
        private val booksCacheMapper: BooksCacheMapper
    ) : BooksRepository {
        override suspend fun fetchBooks() = try {
            val booksCacheList = cacheDataSource.fetchBooks()
            if (booksCacheList.isEmpty()) {
                val booksCloudList = booksCloudDataSource.fetchBooks()
                val books = booksCloudMapper.map(booksCloudList)
                cacheDataSource.saveBooks(books)
                BooksData.Success(books)
            } else {
                BooksData.Success(booksCacheMapper.map(booksCacheList))
            }
        } catch (e: Exception) {
            BooksData.Fail(e)
        }
    }
}