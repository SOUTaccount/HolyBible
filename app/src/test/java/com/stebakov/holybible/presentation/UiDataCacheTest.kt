package com.stebakov.holybible.presentation

import com.stebakov.holybible.presentation.books.BookUi
import com.stebakov.holybible.presentation.books.CollapsedIdsCache
import com.stebakov.holybible.presentation.books.UiDataCache
import org.junit.Assert.*
import org.junit.Test

/**
 * Test for [UiDataCache.Base]
 */

class UiDataCacheTest {

    private val source = listOf(
        BookUi.Testament(-1, "old"),
        BookUi.Base(1, "first"),
        BookUi.Base(2, "second"),
        BookUi.Testament(0, "new"),
        BookUi.Base(3, "third"),
        BookUi.Base(4, "forth"),
    )

    @Test
    fun test_collapse_first() {
        val dataCache = UiDataCache.Base(CollapsedIdsCache.Empty())
        dataCache.cache(source)

        val actual = dataCache.changeState(-1)
        val expected = listOf(
            BookUi.Testament(-1, "old", true),
            BookUi.Testament(0, "new"),
            BookUi.Base(3, "third"),
            BookUi.Base(4, "forth")
        )
        assertEquals(expected, actual)
    }

    @Test
    fun test_collapse_second() {
        val dataCache = UiDataCache.Base(CollapsedIdsCache.Empty())
        dataCache.cache(source)

        val actual = dataCache.changeState(0)
        val expected = listOf(
            BookUi.Testament(-1, "old"),
            BookUi.Base(1, "first"),
            BookUi.Base(2, "second"),
            BookUi.Testament(0, "new", true)
        )
        assertEquals(expected, actual)
    }

    @Test
    fun test_collapse_first_then_expand() {
        val dataCache = UiDataCache.Base(CollapsedIdsCache.Empty())
        dataCache.cache(source)

        var actual = dataCache.changeState(-1)
        var expected = listOf(
            BookUi.Testament(-1, "old", true),
            BookUi.Testament(0, "new"),
            BookUi.Base(3, "third"),
            BookUi.Base(4, "forth")
        )
        assertEquals(expected, actual)

        actual = dataCache.changeState(-1)
        expected = source
        assertEquals(expected, actual)
    }

    @Test
    fun test_collapse_second_then_expand() {
        val dataCache = UiDataCache.Base(CollapsedIdsCache.Empty())
        dataCache.cache(source)

        var actual = dataCache.changeState(0)
        var expected = listOf(
            BookUi.Testament(-1, "old"),
            BookUi.Base(1, "first"),
            BookUi.Base(2, "second"),
            BookUi.Testament(0, "new", true)
        )
        assertEquals(expected, actual)

        actual = dataCache.changeState(0)
        expected = source
        assertEquals(expected, actual)
    }

    @Test
    fun test_collapse_first_collapse_second() {
        val dataCache = UiDataCache.Base(CollapsedIdsCache.Empty())
        dataCache.cache(source)

        var actual = dataCache.changeState(-1)
        var expected = listOf(
            BookUi.Testament(-1, "old", true),
            BookUi.Testament(0, "new"),
            BookUi.Base(3, "third"),
            BookUi.Base(4, "forth")
        )
        assertEquals(expected, actual)

        actual = dataCache.changeState(0)
        expected = listOf(
            BookUi.Testament(-1, "old", true),
            BookUi.Testament(0, "new", true),
        )
        assertEquals(expected, actual)
    }

    @Test
    fun test_collapse_first_collapse_second_expand_first() {
        val dataCache = UiDataCache.Base(CollapsedIdsCache.Empty())
        dataCache.cache(source)

        var actual = dataCache.changeState(-1)
        var expected = listOf(
            BookUi.Testament(-1, "old", true),
            BookUi.Testament(0, "new"),
            BookUi.Base(3, "third"),
            BookUi.Base(4, "forth")
        )
        assertEquals(expected, actual)

        actual = dataCache.changeState(0)
        expected = listOf(
            BookUi.Testament(-1, "old", true),
            BookUi.Testament(0, "new", true),
        )
        assertEquals(expected, actual)

        actual = dataCache.changeState(-1)
        expected = listOf(
            BookUi.Testament(-1, "old"),
            BookUi.Base(1, "first"),
            BookUi.Base(2, "second"),
            BookUi.Testament(0, "new", true)
        )
        assertEquals(expected, actual)
    }

    @Test
    fun test_collapse_first_collapse_second_expand_second() {
        val dataCache = UiDataCache.Base(CollapsedIdsCache.Empty())
        dataCache.cache(source)

        var actual = dataCache.changeState(-1)
        var expected = listOf(
            BookUi.Testament(-1, "old", true),
            BookUi.Testament(0, "new"),
            BookUi.Base(3, "third"),
            BookUi.Base(4, "forth")
        )
        assertEquals(expected, actual)

        actual = dataCache.changeState(0)
        expected = listOf(
            BookUi.Testament(-1, "old", true),
            BookUi.Testament(0, "new", true),
        )
        assertEquals(expected, actual)

        actual = dataCache.changeState(0)
        expected = listOf(
            BookUi.Testament(-1, "old", true),
            BookUi.Testament(0, "new"),
            BookUi.Base(3, "third"),
            BookUi.Base(4, "forth")
        )
        assertEquals(expected, actual)
    }
}