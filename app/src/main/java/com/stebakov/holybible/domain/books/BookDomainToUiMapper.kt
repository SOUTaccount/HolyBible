package com.stebakov.holybible.domain.books

import com.stebakov.holybible.core.Abstract
import com.stebakov.holybible.presentation.books.BookUi

interface BookDomainToUiMapper : Abstract.Mapper {
    fun map(id: Int, name: String): BookUi
}