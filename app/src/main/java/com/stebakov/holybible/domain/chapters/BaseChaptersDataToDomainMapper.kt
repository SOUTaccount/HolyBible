package com.stebakov.holybible.domain.chapters

import com.stebakov.holybible.data.chapters.ChapterData
import com.stebakov.holybible.data.chapters.ChapterDataToDomainMapper
import com.stebakov.holybible.data.chapters.ChaptersDataToDomainMapper
import java.lang.Exception

class BaseChaptersDataToDomainMapper(
    private val mapper: ChapterDataToDomainMapper
) : ChaptersDataToDomainMapper() {

    override fun map(data: List<ChapterData>) = ChaptersDomain.Success(data.map { chapterData ->
        chapterData.map(mapper)
    })

    override fun map(e: Exception) = ChaptersDomain.Fail(errorType(e))
}