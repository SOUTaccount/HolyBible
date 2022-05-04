package com.stebakov.holybible.data.cache

import com.stebakov.holybible.core.Abstract
import com.stebakov.holybible.data.BookData
import com.stebakov.holybible.data.ToBookMapper
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class BookDb : RealmObject(), Abstract.Object<BookData, ToBookMapper> {
    @PrimaryKey
    var id: Int = -1
    var name: String = ""
    var testament: String = ""

    override fun map(mapper: ToBookMapper) = BookData(id, name, testament)

}