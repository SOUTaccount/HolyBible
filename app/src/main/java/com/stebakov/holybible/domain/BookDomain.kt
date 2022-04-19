package com.stebakov.holybible.domain

import com.stebakov.holybible.core.Abstract
import com.stebakov.holybible.core.Book
import com.stebakov.holybible.presentation.BooksUi
import retrofit2.HttpException
import java.lang.Exception
import java.net.UnknownHostException

sealed class BookDomain : Abstract.Object<BooksUi, BooksDomainToUiMapper>() {

    class Success(private val books: List<Book>) : BookDomain() {
        override fun map(mapper: BooksDomainToUiMapper) = mapper.map(books)
    }

    class Fail(private val e: Exception) : BookDomain() {
        override fun map(mapper: BooksDomainToUiMapper) = mapper.map(
            when (e) {
                is UnknownHostException -> ErrorType.NO_CONNECTION
                is HttpException -> ErrorType.SERVICE_UNAVAILABLE
                else -> ErrorType.GENERIC_ERROR
            }
        )
    }
}