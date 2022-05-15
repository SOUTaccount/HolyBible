package com.stebakov.holybible.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stebakov.holybible.core.Read
import com.stebakov.holybible.core.Save
import com.stebakov.holybible.domain.books.BooksDomainToUiMapper
import com.stebakov.holybible.domain.books.BooksInteractor
import com.stebakov.holybible.domain.chapters.ChaptersDomainToUiMapper
import com.stebakov.holybible.domain.chapters.ChaptersInteractor
import com.stebakov.holybible.presentation.books.BookUi
import com.stebakov.holybible.presentation.books.BooksCommunication
import com.stebakov.holybible.presentation.books.UiDataCache
import com.stebakov.holybible.presentation.chapters.ChapterUi
import com.stebakov.holybible.presentation.chapters.ChaptersCommunication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val navigator: Read<Int>,
    private val communication: NavigationCommunication
) : ViewModel() {

    fun init() {
        communication.map(navigator.read())
    }

    fun observe(owner: LifecycleOwner, observer: Observer<Int>) {
        communication.observe(owner, observer)
    }

    fun navigateBack(): Boolean {
        val currentScreen = navigator.read()
        val exit = currentScreen == 0
        if (!exit) {
            val newScreen = currentScreen - 1
            communication.map(newScreen)
        }
        return exit
    }
}