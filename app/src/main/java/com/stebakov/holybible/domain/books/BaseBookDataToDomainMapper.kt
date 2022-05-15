package com.stebakov.holybible.domain.books

import com.stebakov.holybible.data.books.BookDataToDomainMapper

class BaseBookDataToDomainMapper : BookDataToDomainMapper {
    override fun map(id: Int, name: String) = BookDomain.Base(id, name)
}