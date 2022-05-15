package com.stebakov.holybible.data.chapters.cache

import com.stebakov.holybible.core.Abstract
import com.stebakov.holybible.data.chapters.ChapterData
import com.stebakov.holybible.data.chapters.ToChapterMapper

interface ChaptersCacheMapper : Abstract.Mapper.Data<List<ChapterDb>, List<ChapterData>> {

    class Base(private val mapper: ToChapterMapper) : ChaptersCacheMapper {
        override fun map(data: List<ChapterDb>) = data.map { chapterDb ->
            chapterDb.map(mapper)
        }
    }
}