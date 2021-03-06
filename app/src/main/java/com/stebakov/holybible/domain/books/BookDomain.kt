package com.stebakov.holybible.domain.books

import com.stebakov.holybible.core.Abstract
import com.stebakov.holybible.presentation.books.BookUi

sealed class BookDomain : Abstract.Object<BookUi, BookDomainToUiMapper> {


    data class Base(private val id: Int, private val name: String) : BookDomain() {
        override fun map(mapper: BookDomainToUiMapper) = mapper.map(id, name)
    }

    data class Testament(private val type: TestamentType) : BookDomain() {
        override fun map(mapper: BookDomainToUiMapper) = type.map(mapper)
    }
}