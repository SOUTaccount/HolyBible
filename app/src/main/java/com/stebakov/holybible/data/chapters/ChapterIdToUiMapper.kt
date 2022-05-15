package com.stebakov.holybible.data.chapters

import com.stebakov.holybible.R
import com.stebakov.holybible.core.Abstract
import com.stebakov.holybible.core.ResourceProvider
import com.stebakov.holybible.presentation.chapters.ChapterUi

interface ChapterIdToUiMapper : Abstract.Mapper {
    fun map(generatedId: Int, realId: Int): ChapterUi

    class Base(private val resourceProvider: ResourceProvider) : ChapterIdToUiMapper {
        override fun map(generatedId: Int, realId: Int) =
            ChapterUi.Base(generatedId, resourceProvider.getString(R.string.chapter_number, realId))
    }
}