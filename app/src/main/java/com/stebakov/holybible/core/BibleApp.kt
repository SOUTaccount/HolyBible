package com.stebakov.holybible.core

import android.app.Application
import com.stebakov.holybible.data.BooksCloudDataSource
import com.stebakov.holybible.data.BooksCloudMapper
import com.stebakov.holybible.data.BooksRepository
import com.stebakov.holybible.data.cache.BookCacheMapper
import com.stebakov.holybible.data.cache.BooksCacheDataSource
import com.stebakov.holybible.data.cache.BooksCacheMapper
import com.stebakov.holybible.data.cache.RealmProvider
import com.stebakov.holybible.data.net.BookCloudMapper
import com.stebakov.holybible.data.net.BookService
import retrofit2.Retrofit

class BibleApp : Application() {

    private companion object {
        const val BASE_URL = "https://bible-go-api.rkeplin.com/v1/"
    }

    override fun onCreate() {
        super.onCreate()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .build()
        val service = retrofit.create(BookService::class.java)

        val cloudDataSource = BooksCloudDataSource.Base(service)
        val cacheDataSource = BooksCacheDataSource.Base(RealmProvider.Base())
        val booksCloudMapper = BooksCloudMapper.Base(BookCloudMapper.Base())
        val booksCacheMapper = BooksCacheMapper.Base(BookCacheMapper.Base())

        val booksRepository = BooksRepository.Base(
            cloudDataSource,
            cacheDataSource,
            booksCloudMapper,
            booksCacheMapper
        )

    }
}