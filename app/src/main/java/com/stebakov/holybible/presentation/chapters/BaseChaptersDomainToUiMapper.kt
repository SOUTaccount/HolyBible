package com.stebakov.holybible.presentation.chapters

import com.stebakov.holybible.core.ErrorType
import com.stebakov.holybible.core.ResourceProvider
import com.stebakov.holybible.domain.chapters.ChapterDomain
import com.stebakov.holybible.domain.chapters.ChapterDomainToUiMapper
import com.stebakov.holybible.domain.chapters.ChaptersDomainToUiMapper

class BaseChaptersDomainToUiMapper(
    private val mapper: ChapterDomainToUiMapper,
    resourceProvider: ResourceProvider
) : ChaptersDomainToUiMapper(resourceProvider) {

    override fun map(data: List<ChapterDomain>) = ChaptersUi.Base(data.map { chapterDomain ->
        chapterDomain.map(mapper)
    })

    override fun map(errorType: ErrorType) =
        ChaptersUi.Base(listOf(ChapterUi.Fail(errorMessage(errorType))))
}