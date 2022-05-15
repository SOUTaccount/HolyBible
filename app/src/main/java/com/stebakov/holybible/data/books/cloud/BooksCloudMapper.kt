package com.stebakov.holybible.data.books.cloud

import com.stebakov.holybible.core.Abstract
import com.stebakov.holybible.data.books.BookData
import com.stebakov.holybible.data.books.ToBookMapper

interface BooksCloudMapper : Abstract.Mapper.Data<List<BookCloud>, List<BookData>> {

    class Base(private val bookMapper: ToBookMapper) : BooksCloudMapper {
        override fun map(data: List<BookCloud>) = data.map { bookCloud ->
            bookCloud.map(bookMapper)
        }
    }
}