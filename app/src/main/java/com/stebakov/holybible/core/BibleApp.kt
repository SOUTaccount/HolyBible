package com.stebakov.holybible.core

import android.app.Application
import com.google.gson.Gson
import com.stebakov.holybible.data.*
import com.stebakov.holybible.data.cache.BooksCacheDataSource
import com.stebakov.holybible.data.cache.BooksCacheMapper
import com.stebakov.holybible.data.cache.RealmProvider
import com.stebakov.holybible.data.net.BookService
import com.stebakov.holybible.domain.BaseBookDataToDomainMapper
import com.stebakov.holybible.domain.BaseBooksDataToDomainMapper
import com.stebakov.holybible.domain.BooksInteractor
import com.stebakov.holybible.presentation.*
import io.realm.Realm
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

class BibleApp : Application() {

    private companion object {
        const val BASE_URL = "https://bible-go-api.rkeplin.com/v1/"
    }

    lateinit var mainViewModel: MainViewModel

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
        val client = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES) // коннект происходит только 1 мин, после ошибка
            .readTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY // выводит в логи джсон, лог http
            })
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .build()
        val service = retrofit.create(BookService::class.java)
        val gson = Gson()

        val toBookMapper = ToBookMapper.Base()
        val cloudDataSource = BooksCloudDataSource.Base(service, gson)
        val cacheDataSource =
            BooksCacheDataSource.Base(RealmProvider.Base(), BookDataToDbMapper.Base())
        val booksCloudMapper = BooksCloudMapper.Base(toBookMapper)
        val booksCacheMapper = BooksCacheMapper.Base(toBookMapper)

        val booksRepository = BooksRepository.Base(
            cloudDataSource,
            cacheDataSource,
            booksCloudMapper,
            booksCacheMapper
        )
        val booksInteractor = BooksInteractor.Base(
            booksRepository,
            BaseBooksDataToDomainMapper(BaseBookDataToDomainMapper())
        )
        val communication = BooksCommunication.Base()
        val resourceProvider = ResourceProvider.Base(this)
        mainViewModel = MainViewModel(
            booksInteractor,
            BaseBooksDomainToUiMapper(resourceProvider, BaseBookDomainToUiMapper(resourceProvider)),
            communication,
            UiDataCache.Base(IdCache.Base(this))
        )
    }
}