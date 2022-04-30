package com.stebakov.holybible.data

import com.stebakov.holybible.core.Abstract

interface ToBookMapper : Abstract.Mapper {
    fun map(id: Int, name: String): BookData

    class Base : ToBookMapper {
        override fun map(id: Int, name: String) = BookData(id, name)
    }
}