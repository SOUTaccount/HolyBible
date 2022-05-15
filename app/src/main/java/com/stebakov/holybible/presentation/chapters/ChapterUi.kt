package com.stebakov.holybible.presentation.chapters

import com.stebakov.holybible.core.ComparableTextMapper
import com.stebakov.holybible.core.TextMapper

sealed class ChapterUi : ComparableTextMapper<ChapterUi> {

    class Base(
        private val id: Int,
        private val text: String,
    ) : ChapterUi(){
        override fun map(mapper: TextMapper) = mapper.map(text)
    }

    class Fail(
        private val message: String
    ) : ChapterUi(){
        override fun map(mapper: TextMapper) = mapper.map(message)
    }

    object Progress : ChapterUi(){
        override fun map(mapper: TextMapper) = Unit
    }
}