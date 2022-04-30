package com.stebakov.holybible.presentation

import com.stebakov.holybible.domain.BookDomain
import com.stebakov.holybible.domain.BookDomainToUiMapper
import com.stebakov.holybible.domain.BooksDomainToUiMapper
import com.stebakov.holybible.domain.ErrorType

class BaseBooksDomainToUiMapper(
    private val resourceProvider: ResourceProvider,
    private val bookMapper: BookDomainToUiMapper
) :
    BooksDomainToUiMapper {
    override fun map(books: List<BookDomain>) = BooksUi.Success(books, bookMapper)
    override fun map(errorType: ErrorType) = BooksUi.Fail(errorType, resourceProvider)
}