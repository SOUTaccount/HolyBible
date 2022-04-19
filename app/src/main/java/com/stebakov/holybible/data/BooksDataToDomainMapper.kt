package com.stebakov.holybible.data

import com.stebakov.holybible.core.Abstract
import com.stebakov.holybible.core.Book
import com.stebakov.holybible.data.net.BookCloud
import com.stebakov.holybible.domain.BookDomain
import retrofit2.HttpException
import java.lang.Exception
import java.net.UnknownHostException

interface BooksDataToDomainMapper : Abstract.Mapper {
    fun map(books: List<Book>): BookDomain
    fun map(e: Exception): BookDomain
}