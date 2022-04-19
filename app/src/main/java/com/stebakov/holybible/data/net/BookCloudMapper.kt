package com.stebakov.holybible.data.net

import com.stebakov.holybible.core.Abstract
import com.stebakov.holybible.core.Book

interface BookCloudMapper : Abstract.Mapper {
    fun map(id: Int, name: String): Book

    class Base : BookCloudMapper {
        override fun map(id: Int, name: String) = Book(id, name)

    }
}