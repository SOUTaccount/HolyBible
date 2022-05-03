package com.stebakov.holybible.data.cache

import com.stebakov.holybible.data.BookData
import com.stebakov.holybible.data.BookDataToDbMapper


interface BooksCacheDataSource {

    fun fetchBooks(): List<BookDb>

    fun saveBooks(books: List<BookData>)

    class Base(
        private val realmProvider: RealmProvider,
        private val mapper: BookDataToDbMapper
    ) : BooksCacheDataSource {

        override fun fetchBooks(): List<BookDb> {
            realmProvider.provide().use { realm ->
                val booksDb = realm.where(BookDb::class.java).findAll() ?: emptyList()
                return realm.copyFromRealm(booksDb)
            }
        }

        override fun saveBooks(books: List<BookData>) = realmProvider.provide().use { realm ->
            realm.executeTransaction {
                books.forEach { book ->
                    book.mapTo(mapper, DbWrapper.Base(it))
                }
            }
        }
    }
}