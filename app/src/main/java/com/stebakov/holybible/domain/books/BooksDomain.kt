package com.stebakov.holybible.domain.books

import com.stebakov.holybible.core.Abstract
import com.stebakov.holybible.core.ErrorType
import com.stebakov.holybible.presentation.books.BooksUi

sealed class BooksDomain : Abstract.Object<BooksUi, BooksDomainToUiMapper> {

    data class Success(private val books: List<BookDomain>) : BooksDomain() {
        override fun map(mapper: BooksDomainToUiMapper) = mapper.map(books)
    }

    data class Fail(private val errorType: ErrorType) : BooksDomain() {
        override fun map(mapper: BooksDomainToUiMapper) = mapper.map(errorType)
    }
}