package com.stebakov.holybible.domain

import com.stebakov.holybible.core.Abstract
import com.stebakov.holybible.presentation.BookUi

class BookDomain(private val id: Int, private val name: String) :
    Abstract.Object<BookUi, BookDomainToUiMapper> {
    override fun map(mapper: BookDomainToUiMapper) = mapper.map(id, name)
}