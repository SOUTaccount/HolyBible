package com.stebakov.holybible.presentation.books

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stebakov.holybible.core.Save
import com.stebakov.holybible.domain.books.BooksDomainToUiMapper
import com.stebakov.holybible.domain.books.BooksInteractor
import com.stebakov.holybible.presentation.NavigationCommunication
import com.stebakov.holybible.presentation.Screens.Companion.BOOKS_SCREEN
import com.stebakov.holybible.presentation.Screens.Companion.CHAPTERS_SCREEN
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BooksViewModel(
    private val booksInteractor: BooksInteractor,
    private val mapper: BooksDomainToUiMapper,
    private val communication: BooksCommunication,
    private val uiDataCache: UiDataCache,
    private val bookCache: Save<Pair<Int, String>>,
    private val navigator : Save<Int>,
    private val navigationCommunication: NavigationCommunication
) : ViewModel() {

    fun fetchBooks() {
        communication.map(listOf(BookUi.Progress))
        viewModelScope.launch(Dispatchers.IO) {
            val resultDomain = booksInteractor.fetchBooks()
            val resultUi = resultDomain.map(mapper)
            val actual = resultUi.cache(uiDataCache)
            withContext(Dispatchers.Main) {
                actual.map(communication)
            }
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<List<BookUi>>) {
        communication.observe(owner, observer)
    }

    fun collapseOrExpand(id: Int) = communication.map(uiDataCache.changeState(id))

    fun saveCollapsedStates() = uiDataCache.saveState()

    fun showBook(id: Int, name:String) {
        bookCache.save(Pair(id, name))
        navigationCommunication.map(CHAPTERS_SCREEN)
    }

    fun init() {
        navigator.save(BOOKS_SCREEN)
        fetchBooks()
    }
}