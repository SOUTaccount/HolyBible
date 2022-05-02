package com.stebakov.holybible.presentation

import com.stebakov.holybible.R
import com.stebakov.holybible.core.Abstract
import com.stebakov.holybible.domain.BookDomain
import com.stebakov.holybible.domain.BookDomainToUiMapper
import com.stebakov.holybible.domain.ErrorType

sealed class BooksUi : Abstract.Object<Unit, BooksCommunication> {

    data class Success(
        private val books: List<BookDomain>,
        private val bookMapper: BookDomainToUiMapper
    ) : BooksUi() {
        override fun map(mapper: BooksCommunication) {
            val booksUi = books.map {
                it.map(bookMapper)
            }
            mapper.map(booksUi)
        }
    }

    data class Fail(
        private val errorType: ErrorType,
        private val resourceProvider: ResourceProvider
    ) : BooksUi() {
        override fun map(mapper: BooksCommunication) {
            val messageId = when (errorType) {
                ErrorType.SERVICE_UNAVAILABLE -> R.string.service_unavailable_message
                ErrorType.NO_CONNECTION -> R.string.no_connection_message
                else -> R.string.something_went_wrong
            }
            val message = resourceProvider.getString(messageId)
            mapper.map(listOf(BookUi.Fail(message)))
        }
    }
}