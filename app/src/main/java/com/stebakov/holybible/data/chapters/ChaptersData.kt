package com.stebakov.holybible.data.chapters

import com.stebakov.holybible.core.Abstract
import com.stebakov.holybible.domain.chapters.ChaptersDomain
import java.lang.Exception

sealed class ChaptersData : Abstract.Object<ChaptersDomain, ChaptersDataToDomainMapper> {

    data class Success(private val chapters: List<ChapterData>) : ChaptersData() {
        override fun map(mapper: ChaptersDataToDomainMapper) = mapper.map(chapters)
    }

    data class Fail(private val e: Exception) : ChaptersData() {
        override fun map(mapper: ChaptersDataToDomainMapper) = mapper.map(e)
    }
}