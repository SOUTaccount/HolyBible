package com.stebakov.holybible.domain

import com.stebakov.holybible.data.BookData
import com.stebakov.holybible.data.BookDataToDomainMapper
import com.stebakov.holybible.data.BooksDataToDomainMapper
import com.stebakov.holybible.data.TestamentTemp
import retrofit2.HttpException
import java.lang.Exception
import java.net.UnknownHostException

class BaseBooksDataToDomainMapper(private val bookMapper: BookDataToDomainMapper) :
    BooksDataToDomainMapper {
    override fun map(books: List<BookData>): BooksDomain {
        val data = mutableListOf<BookDomain>()
        val temp = TestamentTemp.Base()
        books.forEach { bookData ->
            if (!bookData.compare(temp)) {
                if (temp.isEmpty())
                    data.add(BookDomain.Testament(TestamentType.OLD))
                else
                    data.add(BookDomain.Testament(TestamentType.NEW))
                bookData.saveTestament(temp)
            }
            data.add(bookData.map(bookMapper))
        }
        return BooksDomain.Success(data)
    }

    override fun map(e: Exception): BooksDomain = BooksDomain.Fail(
        when (e) {
            is UnknownHostException -> ErrorType.NO_CONNECTION
            is HttpException -> ErrorType.SERVICE_UNAVAILABLE
            else -> ErrorType.GENERIC_ERROR
        }
    )
}