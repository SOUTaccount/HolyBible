package com.stebakov.holybible.data

import com.stebakov.holybible.core.Abstract
import com.stebakov.holybible.core.Book
import com.stebakov.holybible.domain.BooksDomain
import java.lang.Exception

sealed class BooksData : Abstract.Object<BooksDomain, BooksDataToDomainMapper>() {
    class Success(private val books: List<Book>) : BooksData() {
        override fun map(mapper: BooksDataToDomainMapper): BooksDomain {
            return mapper.map(books)
        }
    }

    class Fail(private val e:Exception): BooksData(){
        override fun map(mapper: BooksDataToDomainMapper): BooksDomain {
            return mapper.map(e)
        }

    }
}