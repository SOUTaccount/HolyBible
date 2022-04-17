package com.stebakov.holybible.data.cache

import com.stebakov.holybible.core.Abstract
import com.stebakov.holybible.core.Book
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class BookDb : RealmObject(), Abstract.Mapable<Book, BookCacheMapper> {
    @PrimaryKey
    var id: Int = -1
    var name: String = ""

    override fun map(mapper: BookCacheMapper) = Book(id, name)

}