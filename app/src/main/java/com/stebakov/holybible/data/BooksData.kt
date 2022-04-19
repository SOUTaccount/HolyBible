package com.stebakov.holybible.data

import com.stebakov.holybible.core.Abstract
import com.stebakov.holybible.core.Book
import com.stebakov.holybible.data.net.BookCloud
import com.stebakov.holybible.domain.BookDomain
import java.lang.Exception

sealed class BooksData : Abstract.Object<BookDomain, BooksDataToDomainMapper>() {
    class Success(private val books: List<Book>) : BooksData() {
        override fun map(mapper: BooksDataToDomainMapper): BookDomain {
            return mapper.map(books)
        }
    }

    class Fail(private val e:Exception): BooksData(){
        override fun map(mapper: BooksDataToDomainMapper): BookDomain {
            return mapper.map(e)
        }

    }
}