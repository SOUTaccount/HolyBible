package com.stebakov.holybible.data.chapters

import com.stebakov.holybible.core.Abstract
import com.stebakov.holybible.core.DbWrapper
import com.stebakov.holybible.data.chapters.cache.ChapterDataToDbMapper
import com.stebakov.holybible.data.chapters.cache.ChapterDb
import com.stebakov.holybible.domain.chapters.ChapterDomain

data class ChapterData(private val chapterId: ChapterId) :
    Abstract.Object.ToDb<ChapterDb, ChapterDataToDbMapper>,
    Abstract.Object<ChapterDomain, ChapterDataToDomainMapper> {

    override fun mapBy(mapper: ChapterDataToDbMapper, db: DbWrapper<ChapterDb>) =
        mapper.mapToDb(chapterId, db)

    override fun map(mapper: ChapterDataToDomainMapper) = mapper.map(chapterId)
}