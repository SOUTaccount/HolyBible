package com.stebakov.holybible.domain

import com.stebakov.holybible.data.BookData
import com.stebakov.holybible.data.BookDataToDomainMapper
import com.stebakov.holybible.data.BooksDataToDomainMapper
import java.lang.Exception

class BaseBooksDataToDomainMapper(private val bookMapper: BookDataToDomainMapper) : BooksDataToDomainMapper {
    override fun map(books: List<BookData>) = BooksDomain.Success(books,bookMapper)
    override fun map(e: Exception) = BooksDomain.Fail(e)
}