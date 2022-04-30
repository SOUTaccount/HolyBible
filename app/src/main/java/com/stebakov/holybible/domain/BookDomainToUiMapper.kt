package com.stebakov.holybible.domain

import com.stebakov.holybible.core.Abstract
import com.stebakov.holybible.presentation.BookUi

interface BookDomainToUiMapper : Abstract.Mapper {
    fun map(id: Int, name: String): BookUi
}