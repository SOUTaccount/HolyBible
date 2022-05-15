package com.stebakov.holybible.core

import android.app.Application
import com.google.gson.Gson
import com.stebakov.holybible.data.books.*
import com.stebakov.holybible.data.books.cache.BookDataToDbMapper
import com.stebakov.holybible.data.books.cache.BooksCacheDataSource
import com.stebakov.holybible.data.books.cache.BooksCacheMapper
import com.stebakov.holybible.data.books.cloud.BookService
import com.stebakov.holybible.data.books.cloud.BooksCloudDataSource
import com.stebakov.holybible.data.books.cloud.BooksCloudMapper
import com.stebakov.holybible.data.chapters.ChapterIdToUiMapper
import com.stebakov.holybible.data.chapters.ChaptersRepository
import com.stebakov.holybible.data.chapters.ToChapterMapper
import com.stebakov.holybible.data.chapters.cache.ChapterDataToDbMapper
import com.stebakov.holybible.data.chapters.cache.ChaptersCacheDataSource
import com.stebakov.holybible.data.chapters.cache.ChaptersCacheMapper
import com.stebakov.holybible.data.chapters.cloud.ChaptersCloudDataSource
import com.stebakov.holybible.data.chapters.cloud.ChaptersCloudMapper
import com.stebakov.holybible.data.chapters.cloud.ChaptersService
import com.stebakov.holybible.domain.books.BaseBookDataToDomainMapper
import com.stebakov.holybible.domain.books.BaseBooksDataToDomainMapper
import com.stebakov.holybible.domain.books.BooksInteractor
import com.stebakov.holybible.domain.chapters.BaseChapterDataToDomainMapper
import com.stebakov.holybible.domain.chapters.BaseChaptersDataToDomainMapper
import com.stebakov.holybible.domain.chapters.ChaptersInteractor
import com.stebakov.holybible.presentation.MainViewModel
import com.stebakov.holybible.presentation.NavigationCommunication
import com.stebakov.holybible.presentation.Navigator
import com.stebakov.holybible.presentation.books.*
import com.stebakov.holybible.presentation.chapters.BaseChapterDomainToUiMapper
import com.stebakov.holybible.presentation.chapters.BaseChaptersDomainToUiMapper
import com.stebakov.holybible.presentation.chapters.ChaptersCommunication
import com.stebakov.holybible.presentation.chapters.ChaptersViewModel
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
    lateinit var booksViewModel: BooksViewModel
    lateinit var chaptersViewModel: ChaptersViewModel

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val client = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .build()
        val service = retrofit.create(BookService::class.java)

        val gson = Gson()
        val cloudDataSource = BooksCloudDataSource.Base(service, gson)
        val realmProvider = RealmProvider.Base()
        val cacheDataSource =
            BooksCacheDataSource.Base(realmProvider, BookDataToDbMapper.Base())
        val toBookMapper = ToBookMapper.Base()
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
        val bookCache = BookCache.Base(this)
        val chaptersRepository = ChaptersRepository.Base(
            ChaptersCloudDataSource.Base(
                retrofit.create(ChaptersService::class.java),
                gson
            ),
            ChaptersCacheDataSource.Base(realmProvider, ChapterDataToDbMapper.Base()),
            ChaptersCloudMapper.Base(ToChapterMapper.Cloud(bookCache)),
            ChaptersCacheMapper.Base(ToChapterMapper.Db(bookCache)),
            bookCache
        )
        val chaptersInteractor = ChaptersInteractor.Base(
            chaptersRepository,
            BaseChaptersDataToDomainMapper(BaseChapterDataToDomainMapper())
        )

        val navigator = Navigator.Base(this)
        val navigationCommunication = NavigationCommunication.Base()
        mainViewModel = MainViewModel(navigator, navigationCommunication)

        booksViewModel = BooksViewModel(
            booksInteractor,
            BaseBooksDomainToUiMapper(resourceProvider, BaseBookDomainToUiMapper(resourceProvider)),
            communication,
            UiDataCache.Base(CollapsedIdsCache.Base(this)),
            bookCache,
            navigator,
            navigationCommunication
        )

        chaptersViewModel = ChaptersViewModel(
            chaptersInteractor,
            ChaptersCommunication.Base(),
            BaseChaptersDomainToUiMapper(
                BaseChapterDomainToUiMapper(ChapterIdToUiMapper.Base(resourceProvider)),
                resourceProvider
            ),
            navigator,
            bookCache
        ) //todo IoC
    }
}