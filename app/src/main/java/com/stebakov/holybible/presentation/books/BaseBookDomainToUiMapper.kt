package com.stebakov.holybible.presentation.books

import com.stebakov.holybible.R
import com.stebakov.holybible.core.ResourceProvider
import com.stebakov.holybible.domain.books.BookDomainToUiMapper
import com.stebakov.holybible.domain.books.TestamentType

class BaseBookDomainToUiMapper(private val resourceProvider: ResourceProvider) :
    BookDomainToUiMapper {
    override fun map(id: Int, name: String) = when {
        TestamentType.NEW.matches(id) -> BookUi.Testament(
            id,
            resourceProvider.getString(R.string.new_testament)
        )
        TestamentType.OLD.matches(id) -> BookUi.Testament(
            id,
            resourceProvider.getString(R.string.old_testament)
        )
        else -> BookUi.Base(id, name)
    }
}