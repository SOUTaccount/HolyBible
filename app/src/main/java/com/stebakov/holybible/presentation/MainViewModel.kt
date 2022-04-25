package com.stebakov.holybible.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stebakov.holybible.core.Abstract
import com.stebakov.holybible.core.Book
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

    fun fetchBooks() = viewModelScope.launch(Dispatchers.IO) {
        val resultDomain = booksInteractor.fetchBooks()
        withContext(Dispatchers.Main) {
            val resultUi = resultDomain.map(mapper)
            resultUi.map(Abstract.Mapper.Empty())
        }
    }

    fun observer(owner: LifecycleOwner, observer: Observer<List<Book>>) {
        communication.observeSuccess(owner, observer)
    }
}