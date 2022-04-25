package com.stebakov.holybible.domain

import com.stebakov.holybible.core.Book
import com.stebakov.holybible.data.BooksDataToDomainMapper
import java.lang.Exception

class BaseBooksDataToDomainMapper : BooksDataToDomainMapper {
    override fun map(books: List<Book>) = BooksDomain.Success(books)
    override fun map(e: Exception) = BooksDomain.Fail(e)
}