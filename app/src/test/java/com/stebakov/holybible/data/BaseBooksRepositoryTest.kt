package com.stebakov.holybible.data

import com.stebakov.holybible.core.Book
import com.stebakov.holybible.data.cache.BookCacheMapper
import com.stebakov.holybible.data.cache.BookDb
import com.stebakov.holybible.data.net.BookCloudMapper

abstract class BaseBooksRepositoryTest {

    protected inner class TestBookCloudMapper : BookCloudMapper {
        override fun map(id: Int, name: String) = Book(id, name)
    }

    protected inner class TestBookCacheMapper : BookCacheMapper {
        override fun map(bookDb: BookDb) = Book(bookDb.id, bookDb.name)
    }
}