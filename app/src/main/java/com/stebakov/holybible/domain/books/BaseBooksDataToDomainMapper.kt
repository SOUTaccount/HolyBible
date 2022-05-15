package com.stebakov.holybible.domain.books

import com.stebakov.holybible.data.books.BookData
import com.stebakov.holybible.data.books.BookDataToDomainMapper
import com.stebakov.holybible.data.books.BooksDataToDomainMapper
import com.stebakov.holybible.data.books.TestamentTemp
import java.lang.Exception

class BaseBooksDataToDomainMapper(private val bookMapper: BookDataToDomainMapper) :
    BooksDataToDomainMapper() {
    override fun map(data: List<BookData>): BooksDomain {
        val domainList = mutableListOf<BookDomain>()
        val temp = TestamentTemp.Base()
        data.forEach { bookData ->
            if (!bookData.matches(temp)) {
                val testamentType = if (temp.isEmpty())
                    TestamentType.OLD
                else
                    TestamentType.NEW
                domainList.add(BookDomain.Testament(testamentType))
                bookData.save(temp)
            }
            domainList.add(bookData.map(bookMapper))
        }
        return BooksDomain.Success(domainList)
    }

    override fun map(e: Exception): BooksDomain = BooksDomain.Fail(errorType(e))
}