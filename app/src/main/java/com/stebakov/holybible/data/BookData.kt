package com.stebakov.holybible.data

import com.stebakov.holybible.core.Abstract
import com.stebakov.holybible.data.cache.BookDb
import com.stebakov.holybible.domain.BookDomain
import io.realm.Realm

class BookData(private val id: Int, private val name: String) :
    Abstract.Object<BookDomain, BookDataToDomainMapper>,
    ToBookDb<BookDb, BookDataToDbMapper> {
    override fun map(mapper: BookDataToDomainMapper) = mapper.map(id, name)
    override fun mapTo(mapper: BookDataToDbMapper, realm: Realm) = mapper.mapToDb(id, name, realm)
}

interface ToBookDb<T, M : Abstract.Mapper> {
    fun mapTo(mapper: M, realm: Realm): T
}