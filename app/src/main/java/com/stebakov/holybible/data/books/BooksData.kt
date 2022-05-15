package com.stebakov.holybible.data.books

import com.stebakov.holybible.core.Abstract
import com.stebakov.holybible.domain.books.BooksDomain
import java.lang.Exception

sealed class BooksData : Abstract.Object<BooksDomain, BooksDataToDomainMapper> {
    data class Success(private val books: List<BookData>) : BooksData() {
        override fun map(mapper: BooksDataToDomainMapper): BooksDomain {
            return mapper.map(books)
        }
    }

    data class Fail(private val e:Exception): BooksData(){
        override fun map(mapper: BooksDataToDomainMapper): BooksDomain {
            return mapper.map(e)
        }
    }
}