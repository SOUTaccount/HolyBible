package com.stebakov.holybible.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stebakov.holybible.domain.BooksDomainToUiMapper
import com.stebakov.holybible.domain.BooksInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val booksInteractor: BooksInteractor,
    private val mapper: BooksDomainToUiMapper,
    private val communication: BooksCommunication
) : ViewModel() {

    fun fetchBooks() {
        communication.map(listOf(BookUi.Progress))
        viewModelScope.launch(Dispatchers.IO) {
            val resultDomain = booksInteractor.fetchBooks()
            val resultUi = resultDomain.map(mapper)
            withContext(Dispatchers.Main) {
                resultUi.map(communication)
            }
        }
    }

    fun observer(owner: LifecycleOwner, observer: Observer<List<BookUi>>) {
        communication.observe(owner, observer)
    }
}