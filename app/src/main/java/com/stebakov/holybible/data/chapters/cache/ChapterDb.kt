package com.stebakov.holybible.data.chapters.cache

import com.stebakov.holybible.core.Abstract
import com.stebakov.holybible.data.chapters.ChapterData
import com.stebakov.holybible.data.chapters.ToChapterMapper
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class ChapterDb : RealmObject(), Abstract.Object<ChapterData, ToChapterMapper> {

    /**
     * BookId * 1000 + chapterId
     */
    @PrimaryKey
    var id: Int = -1

    override fun map(mapper: ToChapterMapper) = mapper.map(id)
}