package com.stebakov.holybible.domain

import com.stebakov.holybible.core.Abstract
import com.stebakov.holybible.core.Book
import com.stebakov.holybible.presentation.BooksUi

interface BooksDomainToUiMapper : Abstract.Mapper {
    fun map(books: List<Book>) : BooksUi
    fun map(errorType: ErrorType) : BooksUi
}