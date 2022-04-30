package com.stebakov.holybible.data

import com.stebakov.holybible.core.Abstract
import com.stebakov.holybible.domain.BooksDomain
import java.lang.Exception

interface BooksDataToDomainMapper : Abstract.Mapper {
    fun map(books: List<BookData>): BooksDomain
    fun map(e: Exception): BooksDomain
}