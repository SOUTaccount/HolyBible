package com.stebakov.holybible.presentation

import com.stebakov.holybible.R
import com.stebakov.holybible.domain.BookDomainToUiMapper
import com.stebakov.holybible.domain.TestamentType

class BaseBookDomainToUiMapper(private val resourceProvider: ResourceProvider) :
    BookDomainToUiMapper {
    override fun map(id: Int, name: String) = when (id) {
        TestamentType.NEW.getId() -> BookUi.Testament(
            id,
            resourceProvider.getString(R.string.new_testament)
        )
        TestamentType.OLD.getId() -> BookUi.Testament(
            id,
            resourceProvider.getString(R.string.old_testament)
        )
        else -> BookUi.Base(id, name)
    }
}