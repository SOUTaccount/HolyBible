package com.stebakov.holybible.data.chapters.cache

import com.stebakov.holybible.core.Abstract
import com.stebakov.holybible.core.DbWrapper
import com.stebakov.holybible.data.chapters.ChapterId

interface ChapterDataToDbMapper : Abstract.Mapper {

    fun mapToDb(chapterId: ChapterId, db: DbWrapper<ChapterDb>): ChapterDb

    class Base : ChapterDataToDbMapper {
        override fun mapToDb(chapterId: ChapterId, db: DbWrapper<ChapterDb>) = chapterId.mapToDb(db)
    }
}