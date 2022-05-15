package com.stebakov.holybible.domain.chapters

import com.stebakov.holybible.data.chapters.ChaptersDataToDomainMapper
import com.stebakov.holybible.data.chapters.ChaptersRepository

interface ChaptersInteractor {

    suspend fun fetchChapters(): ChaptersDomain

    class Base(
        private val repository: ChaptersRepository,
        private val mapper: ChaptersDataToDomainMapper
    ) : ChaptersInteractor {
        override suspend fun fetchChapters() = repository.fetchChapters().map(mapper)
    }
}