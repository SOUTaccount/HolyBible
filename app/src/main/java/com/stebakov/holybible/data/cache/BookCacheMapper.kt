package com.stebakov.holybible.data.cache

import com.stebakov.holybible.core.Abstract
import com.stebakov.holybible.core.Book

interface BookCacheMapper : Abstract.Mapper {

    fun map(bookDb: BookDb): Book

    class Base : BookCacheMapper {
        override fun map(bookDb: BookDb) = Book(bookDb.id, bookDb.name)
    }
}