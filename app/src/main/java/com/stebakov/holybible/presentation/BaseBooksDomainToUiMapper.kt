package com.stebakov.holybible.presentation

import com.stebakov.holybible.core.Book
import com.stebakov.holybible.domain.BooksDomainToUiMapper
import com.stebakov.holybible.domain.ErrorType

class BaseBooksDomainToUiMapper(
    private val communication: BooksCommunication,
    private val resourceProvider: ResourceProvider
) :
    BooksDomainToUiMapper {
    override fun map(books: List<Book>) = BooksUi.Success(communication, books)
    override fun map(errorType: ErrorType) =
        BooksUi.Fail(communication, errorType, resourceProvider)
}