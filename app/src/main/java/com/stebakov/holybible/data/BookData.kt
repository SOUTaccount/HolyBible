package com.stebakov.holybible.data

import com.stebakov.holybible.core.Abstract
import com.stebakov.holybible.data.cache.BookDb
import com.stebakov.holybible.data.cache.DbWrapper
import com.stebakov.holybible.domain.BookDomain
import io.realm.Realm

data class BookData(
    private val id: Int, private val name: String, private val testament: String
) : Abstract.Object<BookDomain, BookDataToDomainMapper>,
    ToBookDb<BookDb, BookDataToDbMapper> {
    override fun map(mapper: BookDataToDomainMapper) = mapper.map(id, name)
    override fun mapTo(mapper: BookDataToDbMapper, dbWrapper: DbWrapper) =
        mapper.mapToDb(id, name, testament, dbWrapper)

    fun compare(temp: TestamentTemp) = temp.matches(testament)
    fun saveTestament(temp: TestamentTemp) = temp.save(testament)
}

interface TestamentTemp {
    fun save(testament: String)
    fun matches(testament: String): Boolean
    fun isEmpty() : Boolean

    class Base : TestamentTemp {
        private var temp: String = ""
        override fun save(testament: String) {
            temp = testament
        }

        override fun matches(testament: String) = temp == testament
        override fun isEmpty() = temp.isEmpty()
    }
}

interface ToBookDb<T, M : Abstract.Mapper> {
    fun mapTo(mapper: M, dbWrapper: DbWrapper): T
}