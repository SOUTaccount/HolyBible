package com.stebakov.holybible.presentation.books

import com.stebakov.holybible.domain.books.BookDomain
import com.stebakov.holybible.domain.books.BookDomainToUiMapper
import com.stebakov.holybible.domain.books.BooksDomainToUiMapper
import com.stebakov.holybible.core.ErrorType
import com.stebakov.holybible.core.ResourceProvider

class BaseBooksDomainToUiMapper(
    resourceProvider: ResourceProvider,
    private val bookMapper: BookDomainToUiMapper
) : BooksDomainToUiMapper(resourceProvider) {

    override fun map(data: List<BookDomain>) = BooksUi.Base(data.map {
        it.map(bookMapper)
    })

    override fun map(errorType: ErrorType) =
        BooksUi.Base(listOf(BookUi.Fail(errorMessage(errorType))))
}