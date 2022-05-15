package com.stebakov.holybible.data.books

import com.stebakov.holybible.core.Abstract
import com.stebakov.holybible.domain.books.BookDomain

interface BookDataToDomainMapper : Abstract.Mapper {
    fun map(id: Int, name: String) : BookDomain
}