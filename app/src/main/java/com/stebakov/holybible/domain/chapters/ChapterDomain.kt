package com.stebakov.holybible.domain.chapters

import com.stebakov.holybible.core.Abstract
import com.stebakov.holybible.data.chapters.ChapterId
import com.stebakov.holybible.presentation.chapters.ChapterUi

data class ChapterDomain(private val chapterId: ChapterId) :
    Abstract.Object<ChapterUi, ChapterDomainToUiMapper> {
    override fun map(mapper: ChapterDomainToUiMapper) = mapper.map(chapterId)
}