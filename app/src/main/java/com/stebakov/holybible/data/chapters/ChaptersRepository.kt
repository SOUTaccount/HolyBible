package com.stebakov.holybible.data.chapters

import com.stebakov.holybible.core.Read
import com.stebakov.holybible.data.chapters.cache.ChaptersCacheDataSource
import com.stebakov.holybible.data.chapters.cache.ChaptersCacheMapper
import com.stebakov.holybible.data.chapters.cloud.ChaptersCloudDataSource
import com.stebakov.holybible.data.chapters.cloud.ChaptersCloudMapper
import java.lang.Exception

interface ChaptersRepository  {

    suspend fun fetchChapters() : ChaptersData

    class Base(
        private val cloudDataSource: ChaptersCloudDataSource,
        private val cacheDataSource: ChaptersCacheDataSource,
        private val cloudMapper: ChaptersCloudMapper,
        private val cacheMapper: ChaptersCacheMapper,
        private val bookIdContainer: Read<Pair<Int, String>>
    ) : ChaptersRepository {
        override suspend fun fetchChapters() = try { //todo make base repository
            val bookId = bookIdContainer.read().first
            val chaptersCacheList = cacheDataSource.fetchChapters(bookId)
            if (chaptersCacheList.isEmpty()) {
                val chaptersCloudList = cloudDataSource.fetchChapters(bookId)
                val chapters = cloudMapper.map(chaptersCloudList, bookId)
                cacheDataSource.save(chapters)
                ChaptersData.Success(chapters)
            } else {
                ChaptersData.Success(cacheMapper.map(chaptersCacheList))
            }
        } catch (e: Exception) {
            ChaptersData.Fail(e)
        }
    }
}