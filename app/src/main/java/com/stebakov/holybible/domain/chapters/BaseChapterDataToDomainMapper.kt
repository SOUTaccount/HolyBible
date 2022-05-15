package com.stebakov.holybible.domain.chapters

import com.stebakov.holybible.data.chapters.ChapterDataToDomainMapper
import com.stebakov.holybible.data.chapters.ChapterId

class BaseChapterDataToDomainMapper : ChapterDataToDomainMapper {
    override fun map(data: ChapterId) = ChapterDomain(data)
}