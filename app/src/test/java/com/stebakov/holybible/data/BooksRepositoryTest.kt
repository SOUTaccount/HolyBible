package com.stebakov.holybible.data

import com.stebakov.holybible.data.books.*
import com.stebakov.holybible.data.books.cache.BookDb
import com.stebakov.holybible.data.books.cache.BooksCacheDataSource
import com.stebakov.holybible.data.books.cache.BooksCacheMapper
import com.stebakov.holybible.data.books.cloud.BookCloud
import com.stebakov.holybible.data.books.cloud.BooksCloudDataSource
import com.stebakov.holybible.data.books.cloud.BooksCloudMapper
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import java.net.UnknownHostException

class BooksRepositoryTest : BaseBooksRepositoryTest() {
//
//    private val unknownHostException = UnknownHostException()
//
//    @Test
//    fun test_no_connection_no_cache() = runBlocking {
//        val testCloudDataSource = TestBooksCloudDataSource(returnSuccess = false)
//        val testCacheDataSource = TestBooksCacheDataSource(returnSuccess = false)
//        val repository = BooksRepository.Base(
//            testCloudDataSource,
//            testCacheDataSource,
//            BooksCloudMapper.Base(TestToBookMapper()),
//            BooksCacheMapper.Base(TestToBookMapper())
//        )
//
//        val actual = repository.fetchBooks()
//        val expected = BooksData.Fail(unknownHostException)
//
//        assertEquals(expected, actual)
//    }
//
//    @Test
//    fun test_cloud_success_no_cache() = runBlocking {
//        val testCloudDataSource = TestBooksCloudDataSource(returnSuccess = true)
//        val testCacheDataSource = TestBooksCacheDataSource(returnSuccess = false)
//        val repository = BooksRepository.Base(
//            testCloudDataSource,
//            testCacheDataSource,
//            BooksCloudMapper.Base(TestToBookMapper()),
//            BooksCacheMapper.Base(TestToBookMapper())
//        )
//
//        val actual = repository.fetchBooks()
//        val expected = BooksData.Success(
//            listOf(
//                BookData(0, "name0",""),
//                BookData(1, "name1",""),
//                BookData(2, "name2","")
//            )
//        )
//
//        assertEquals(expected, actual)
//    }
//
//    @Test
//    fun test_no_connection_with_cache() = runBlocking {
//        val testCloudDataSource = TestBooksCloudDataSource(returnSuccess = false)
//        val testCacheDataSource = TestBooksCacheDataSource(returnSuccess = true)
//        val repository = BooksRepository.Base(
//            testCloudDataSource,
//            testCacheDataSource,
//            BooksCloudMapper.Base(TestToBookMapper()),
//            BooksCacheMapper.Base(TestToBookMapper())
//        )
//
//        val actual = repository.fetchBooks()
//        val expected = BooksData.Success(
//            listOf(
//                BookData(10, "name10",""),
//                BookData(11, "name11",""),
//                BookData(12, "name12","")
//            )
//        )
//
//        assertEquals(expected, actual)
//    }
//
//    @Test
//    fun test_cloud_success_with_cache() = runBlocking {
//        val testCloudDataSource = TestBooksCloudDataSource(returnSuccess = true)
//        val testCacheDataSource = TestBooksCacheDataSource(returnSuccess = true)
//        val repository = BooksRepository.Base(
//            testCloudDataSource,
//            testCacheDataSource,
//            BooksCloudMapper.Base(TestToBookMapper()),
//            BooksCacheMapper.Base(TestToBookMapper())
//        )
//
//        val actual = repository.fetchBooks()
//        val expected = BooksData.Success(
//            listOf(
//                BookData(10, "name10",""),
//                BookData(11, "name11",""),
//                BookData(12, "name12","")
//            )
//        )
//        assertEquals(expected, actual)
//    }
//
//    private inner class TestBooksCloudDataSource(
//        private val returnSuccess: Boolean
//    ) : BooksCloudDataSource {
//        override suspend fun fetchBooks(): List<BookCloud> {
//            if (returnSuccess) {
//                return listOf(
//                    BookCloud(0, "name0",""),
//                    BookCloud(1, "name1",""),
//                    BookCloud(2, "name2",""),
//                )
//            } else {
//                throw unknownHostException
//            }
//        }
//    }
//
//    private inner class TestBooksCacheDataSource(
//        private val returnSuccess: Boolean
//    ) : BooksCacheDataSource {
//        override fun fetchBooks(): List<BookDb> {
//            return if (returnSuccess) {
//                listOf(
//                    BookDb().apply {
//                        id = 10
//                        name = "name10"
//                    },
//                    BookDb().apply {
//                        id = 11
//                        name = "name11"
//                    },
//                    BookDb().apply {
//                        id = 12
//                        name = "name12"
//                    }
//                )
//            } else {
//                emptyList()
//            }
//
//        }
//
//        override fun saveBooks(books: List<BookData>) {
//        }
//
//    }

}