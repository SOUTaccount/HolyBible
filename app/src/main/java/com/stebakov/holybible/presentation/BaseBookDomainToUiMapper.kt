package com.stebakov.holybible.presentation

import com.stebakov.holybible.domain.BookDomainToUiMapper

class BaseBookDomainToUiMapper : BookDomainToUiMapper {
    override fun map(id: Int, name: String) = BookUi.Base(id, name)
}