package com.stebakov.holybible.data

import com.stebakov.holybible.core.Abstract
import com.stebakov.holybible.core.Book
import com.stebakov.holybible.domain.BooksDomain
import java.lang.Exception

interface BooksDataToDomainMapper : Abstract.Mapper {
    fun map(books: List<Book>): BooksDomain
    fun map(e: Exception): BooksDomain
}