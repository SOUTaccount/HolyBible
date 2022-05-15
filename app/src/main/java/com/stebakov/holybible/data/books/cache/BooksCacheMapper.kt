package com.stebakov.holybible.data.books.cache

import com.stebakov.holybible.core.Abstract
import com.stebakov.holybible.data.books.BookData
import com.stebakov.holybible.data.books.CommonBookData
import com.stebakov.holybible.data.books.ToBookMapper

interface BooksCacheMapper : Abstract.Mapper.Data<List<CommonBookData>, List<BookData>> {

    class Base(private val mapper: ToBookMapper) : BooksCacheMapper {
        override fun map(data: List<CommonBookData>) = data.map { bookDb ->
            bookDb.map(mapper)
        }
    }
}