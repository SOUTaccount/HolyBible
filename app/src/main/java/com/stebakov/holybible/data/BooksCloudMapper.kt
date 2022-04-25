package com.stebakov.holybible.data

import com.stebakov.holybible.core.Abstract
import com.stebakov.holybible.core.Book
import com.stebakov.holybible.data.net.BookCloud
import com.stebakov.holybible.data.net.BookCloudMapper

interface BooksCloudMapper : Abstract.Mapper {

    fun map(cloudList: List<BookCloud>): List<Book>

    class Base(private val bookMapper: BookCloudMapper) : BooksCloudMapper {
        override fun map(cloudList: List<BookCloud>): List<Book> {
            return cloudList.map { bookCloud ->
                bookCloud.map(bookMapper)
            }
        }
    }
}