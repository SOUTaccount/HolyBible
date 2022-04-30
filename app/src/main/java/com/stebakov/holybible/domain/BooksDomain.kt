package com.stebakov.holybible.domain

import com.stebakov.holybible.core.Abstract
import com.stebakov.holybible.data.BookData
import com.stebakov.holybible.data.BookDataToDomainMapper
import com.stebakov.holybible.presentation.BooksUi
import retrofit2.HttpException
import java.lang.Exception
import java.net.UnknownHostException

sealed class BooksDomain : Abstract.Object<BooksUi, BooksDomainToUiMapper> {

    class Success(
        private val books: List<BookData>,
        private val bookMapper: BookDataToDomainMapper
    ) : BooksDomain() {
        override fun map(mapper: BooksDomainToUiMapper) = mapper.map(books.map {
            it.map(bookMapper)
        })
    }

    class Fail(private val e: Exception) : BooksDomain() {
        override fun map(mapper: BooksDomainToUiMapper) = mapper.map(
            when (e) {
                is UnknownHostException -> ErrorType.NO_CONNECTION
                is HttpException -> ErrorType.SERVICE_UNAVAILABLE
                else -> ErrorType.GENERIC_ERROR
            }
        )
    }
}