package com.stebakov.holybible.data

import com.stebakov.holybible.core.Book
import com.stebakov.holybible.data.cache.BookDb
import com.stebakov.holybible.data.cache.BooksCacheDataSource
import com.stebakov.holybible.data.cache.BooksCacheMapper
import com.stebakov.holybible.data.net.BookCloud
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.Assert.*


class BooksRepositorySaveBooksTest : BaseBooksRepositoryTest() {

    @Test
    fun test_save_books() = runBlocking{
        val testCloudDataSource = TestBooksCloudDataSource()
        val testCacheDataSource = TestBooksCacheDataSource()
        val repository = BooksRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            BooksCloudMapper.Base(TestBookCloudMapper()),
            BooksCacheMapper.Base(TestBookCacheMapper())
        )

        val actualCloud = repository.fetchBooks()
        val expectedCloud = BooksData.Success(listOf(
            Book(0, "name0"),
            Book(1, "name1"),
        ))

        assertEquals(actualCloud,expectedCloud)

        val actualCache = repository.fetchBooks()
        val expectedCache = BooksData.Success(listOf(
            Book(0, "name0 db"),
            Book(1, "name1 db"),
        ))

        assertEquals(actualCache,expectedCache)


    }

    private inner class TestBooksCloudDataSource() : BooksCloudDataSource {
        override suspend fun fetchBooks(): List<BookCloud> {
            return listOf(
                BookCloud(0, "name0"),
                BookCloud(1, "name1")
            )
        }
    }

    private inner class TestBooksCacheDataSource: BooksCacheDataSource {

        private val list = ArrayList<BookDb>()

        override fun fetchBooks(): List<BookDb> {
            return list
        }

        override fun saveBooks(books: List<Book>) {
            books.map { book ->
                list.add(BookDb().apply {
                    this.id = book.id
                    this.name = "${book.name} db"
                })
            }
        }

    }
}