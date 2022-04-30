package com.stebakov.holybible.domain

import com.stebakov.holybible.data.BookDataToDomainMapper

class BaseBookDataToDomainMapper: BookDataToDomainMapper {
    override fun map(id: Int, name: String) = BookDomain(id,name)
}