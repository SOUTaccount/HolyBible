package com.stebakov.holybible.data.chapters.cloud

import com.google.gson.annotations.SerializedName
import com.stebakov.holybible.core.Abstract
import com.stebakov.holybible.data.chapters.ChapterData
import com.stebakov.holybible.data.chapters.ToChapterMapper
import java.lang.IllegalStateException

data class ChaptersCloud(
    @SerializedName("id")
    private val id: Int
) : Abstract.Object<ChapterData, ToChapterMapper> {
    override fun map(mapper: ToChapterMapper) = mapper.map(id)
}