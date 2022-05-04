package com.stebakov.holybible.presentation

import com.stebakov.holybible.R
import com.stebakov.holybible.domain.BookDomain
import com.stebakov.holybible.domain.BookDomainToUiMapper
import com.stebakov.holybible.domain.BooksDomainToUiMapper
import com.stebakov.holybible.domain.ErrorType

class BaseBooksDomainToUiMapper(
    private val resourceProvider: ResourceProvider,
    private val bookMapper: BookDomainToUiMapper
) :
    BooksDomainToUiMapper {
    override fun map(books: List<BookDomain>) = BooksUi.Base(books.map {
        it.map(bookMapper)
    })

    override fun map(errorType: ErrorType): BooksUi {
        val messageId = when (errorType) {
            ErrorType.SERVICE_UNAVAILABLE -> R.string.service_unavailable_message
            ErrorType.NO_CONNECTION -> R.string.no_connection_message
            else -> R.string.something_went_wrong
        }
        val message = resourceProvider.getString(messageId)
        return BooksUi.Base(listOf(BookUi.Fail(message)))

    }
}