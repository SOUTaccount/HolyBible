package com.stebakov.holybible.presentation

import com.stebakov.holybible.core.Abstract

sealed class BookUi : Abstract.Object<Unit, BookUi.StringMapper> {

    override fun map(mapper: StringMapper) = Unit

    object Progress : BookUi()

    class Base(private val id: Int, private val name: String) : BookUi(){
        override fun map(mapper: StringMapper) = mapper.map(name)
    }

    class Fail(private val message: String) : BookUi(){
        override fun map(mapper: StringMapper) = mapper.map(message)
    }

    interface StringMapper : Abstract.Mapper {
        fun map(text: String)
    }
}