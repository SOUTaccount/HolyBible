package com.stebakov.holybible.data.chapters.cloud

import com.stebakov.holybible.core.Abstract
import com.stebakov.holybible.data.chapters.ChapterData
import com.stebakov.holybible.data.chapters.ToChapterMapper

interface ChaptersCloudMapper : Abstract.Mapper {

    fun map(chapters: List<ChaptersCloud>, bookId: Int): List<ChapterData>

    class Base(private val mapper: ToChapterMapper) : ChaptersCloudMapper {
        override fun map(chapters: List<ChaptersCloud>, bookId: Int) =
            chapters.map { chaptersCloud ->
                chaptersCloud.map(mapper)
            }
    }
}