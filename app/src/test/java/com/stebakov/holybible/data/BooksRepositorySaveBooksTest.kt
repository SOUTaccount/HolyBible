package com.stebakov.holybible.data

import com.stebakov.holybible.data.books.*
import com.stebakov.holybible.data.books.cache.BookDb
import com.stebakov.holybible.data.books.cache.BooksCacheDataSource
import com.stebakov.holybible.data.books.cache.BooksCacheMapper
import com.stebakov.holybible.core.DbWrapper
import com.stebakov.holybible.data.books.cache.BookDataToDbMapper
import com.stebakov.holybible.data.books.cloud.BookCloud
import com.stebakov.holybible.data.books.cloud.BooksCloudDataSource
import com.stebakov.holybible.data.books.cloud.BooksCloudMapper
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.Assert.*


class BooksRepositorySaveBooksTest : BaseBooksRepositoryTest() {

//    @Test
//    fun test_save_books() = runBlocking {
//        val testCloudDataSource = TestBooksCloudDataSource()
//        val testCacheDataSource = TestBooksCacheDataSource()
//        val repository = BooksRepository.Base(
//            testCloudDataSource,
//            testCacheDataSource,
//            BooksCloudMapper.Base(TestToBookMapper()),
//            BooksCacheMapper.Base(TestToBookMapper())
//        )
//
//        val actualCloud = repository.fetchBooks()
//        val expectedCloud = BooksData.Success(
//            listOf(
//                BookData(0, "name0", ""),
//                BookData(1, "name1", ""),
//            )
//        )
//
//        assertEquals(actualCloud, expectedCloud)
//
//        val actualCache = repository.fetchBooks()
//        val expectedCache = BooksData.Success(
//            listOf(
//                BookData(0, "name0 db", ""),
//                BookData(1, "name1 db", ""),
//            )
//        )
//
//        assertEquals(actualCache, expectedCache)
//
//
//    }
//
//    private inner class TestBooksCloudDataSource() : BooksCloudDataSource {
//        override suspend fun fetchBooks(): List<BookCloud> {
//            return listOf(
//                BookCloud(0, "name0", ""),
//                BookCloud(1, "name1", "")
//            )
//        }
//    }
//
//    private inner class TestBooksCacheDataSource : BooksCacheDataSource {
//
//        private val list = ArrayList<BookDb>()
//
//        override fun fetchBooks(): List<BookDb> {
//            return list
//        }
//
//        override fun saveBooks(books: List<BookData>) {
//            books.map { book ->
//                list.add(book.mapTo(object : BookDataToDbMapper {
//                    override fun mapToDb(
//                        id: Int,
//                        name: String,
//                        testament: String,
//                        dbWrapper: DbWrapper
//                    ): BookDb = BookDb().apply {
//                        this.id = id
//                        this.name = name
//                        this.testament = testament
//                    }
//
//                }, object : DbWrapper {
//                    override fun createObject(id: Int) = BookDb().apply {
//                        this.id = id
//                    }
//                }))
//            }
//        }
//    }
}