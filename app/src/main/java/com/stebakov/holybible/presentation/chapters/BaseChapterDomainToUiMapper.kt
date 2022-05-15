package com.stebakov.holybible.presentation.chapters

import com.stebakov.holybible.R
import com.stebakov.holybible.core.ResourceProvider
import com.stebakov.holybible.data.chapters.ChapterId
import com.stebakov.holybible.data.chapters.ChapterIdToUiMapper
import com.stebakov.holybible.domain.chapters.ChapterDomainToUiMapper

class BaseChapterDomainToUiMapper(private val mapper: ChapterIdToUiMapper) :
    ChapterDomainToUiMapper {

    override fun map(data: ChapterId) = data.map(mapper)
}