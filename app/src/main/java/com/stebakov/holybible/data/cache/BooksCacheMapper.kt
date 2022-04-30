package com.stebakov.holybible.data.cache

import com.stebakov.holybible.core.Abstract
import com.stebakov.holybible.data.BookData
import com.stebakov.holybible.data.ToBookMapper

interface BooksCacheMapper : Abstract.Mapper {

    fun map(books: List<Abstract.Object<BookData, ToBookMapper>>): List<BookData>

    class Base(private val mapper: ToBookMapper) : BooksCacheMapper {
        override fun map(books: List<Abstract.Object<BookData, ToBookMapper>>) =
            books.map { bookDb ->
                bookDb.map(mapper)
            }
    }
}