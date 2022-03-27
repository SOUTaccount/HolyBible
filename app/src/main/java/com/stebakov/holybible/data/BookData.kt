package com.stebakov.holybible.data

import com.stebakov.holybible.core.Abstract
import com.stebakov.holybible.data.net.BookServerModel
import com.stebakov.holybible.domain.BookDomain
import java.lang.Exception

sealed class BookData : Abstract.Object<BookDomain, BooksDataToDomainMapper>() {
    class Success(private val books: List<BookServerModel>) : BookData() {
        override fun map(mapper: BooksDataToDomainMapper): BookDomain {
            return mapper.map(books)
        }
    }

    class Fail(private val e:Exception): BookData(){
        override fun map(mapper: BooksDataToDomainMapper): BookDomain {
            return mapper.map(e)
        }

    }
}