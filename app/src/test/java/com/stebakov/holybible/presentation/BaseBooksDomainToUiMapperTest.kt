package com.stebakov.holybible.presentation

import com.stebakov.holybible.R
import com.stebakov.holybible.domain.books.BookDomainToUiMapper
import com.stebakov.holybible.core.ErrorType
import com.stebakov.holybible.core.ResourceProvider
import com.stebakov.holybible.presentation.books.BaseBooksDomainToUiMapper
import com.stebakov.holybible.presentation.books.BookUi
import com.stebakov.holybible.presentation.books.BooksUi
import org.junit.Assert.*
import org.junit.Test
import java.lang.IllegalStateException

class BaseBooksDomainToUiMapperTest {

    @Test
    fun test_fail() {
        val resourceProvider = TestResourceProvider()
        val mapper = BaseBooksDomainToUiMapper(resourceProvider, object : BookDomainToUiMapper {
            override fun map(id: Int, name: String): BookUi {
                throw IllegalStateException("not used here")
            }
        })
        var actual = mapper.map(ErrorType.NO_CONNECTION)
        var expected = BooksUi.Base(listOf(BookUi.Fail("noConnection")))
        assertEquals(expected, actual)
        actual = mapper.map(ErrorType.SERVICE_UNAVAILABLE)
        expected = BooksUi.Base(listOf(BookUi.Fail("serviceUnavailable")))
        assertEquals(expected, actual)
        actual = mapper.map(ErrorType.GENERIC_ERROR)
        expected = BooksUi.Base(listOf(BookUi.Fail("somethingWrong")))
        assertEquals(expected, actual)
    }

    private inner class TestResourceProvider : ResourceProvider {
        override fun getString(id: Int): String {
            return when (id) {
                R.string.service_unavailable_message -> "serviceUnavailable"
                R.string.no_connection_message -> "noConnection"
                else -> "somethingWrong"
            }
        }
    }
}