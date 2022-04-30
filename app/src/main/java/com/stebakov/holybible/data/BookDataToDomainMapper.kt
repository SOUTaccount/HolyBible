package com.stebakov.holybible.data

import com.stebakov.holybible.core.Abstract
import com.stebakov.holybible.domain.BookDomain

interface BookDataToDomainMapper : Abstract.Mapper {
    fun map(id: Int, name: String) : BookDomain
}