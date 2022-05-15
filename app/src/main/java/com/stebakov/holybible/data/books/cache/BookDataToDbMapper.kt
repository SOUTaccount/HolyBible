package com.stebakov.holybible.data.books.cache

import com.stebakov.holybible.core.Abstract
import com.stebakov.holybible.core.DbWrapper

interface BookDataToDbMapper : Abstract.Mapper {

    fun mapToDb(id: Int, name: String, testament: String, dbWrapper: DbWrapper<BookDb>): BookDb

    class Base : BookDataToDbMapper {
        override fun mapToDb(id: Int, name: String, testament: String, dbWrapper: DbWrapper<BookDb>): BookDb {
            val bookDb = dbWrapper.createObject(id)
            bookDb.name = name
            bookDb.testament = testament
            return bookDb
        }
    }
}