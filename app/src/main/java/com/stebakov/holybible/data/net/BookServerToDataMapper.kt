package com.stebakov.holybible.data.net

import com.stebakov.holybible.core.Abstract
import com.stebakov.holybible.data.BookData

interface BookServerToDataMapper : Abstract.Mapper {
    fun map(id: Int, name: String): BookData
}