package com.stebakov.holybible.presentation.chapters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stebakov.holybible.core.Read
import com.stebakov.holybible.core.Save
import com.stebakov.holybible.domain.chapters.ChaptersDomainToUiMapper
import com.stebakov.holybible.domain.chapters.ChaptersInteractor
import com.stebakov.holybible.presentation.Screens.Companion.CHAPTERS_SCREEN
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChaptersViewModel(
    private val chaptersInteractor: ChaptersInteractor,
    private val chaptersCommunication: ChaptersCommunication,
    private val chaptersMapper: ChaptersDomainToUiMapper,
    private val navigator: Save<Int>,
    private val bookCache: Read<Pair<Int, String>>
) : ViewModel() {

    fun observeChapters(owner: LifecycleOwner, observer: Observer<List<ChapterUi>>) {
        chaptersCommunication.observe(owner, observer)
    }

    fun fetchChapters() {
        chaptersCommunication.map(listOf(ChapterUi.Progress))
        viewModelScope.launch(Dispatchers.IO) {
            val chapters = chaptersInteractor.fetchChapters()
            val chaptersUi = chapters.map(chaptersMapper)
            withContext(Dispatchers.Main) {
                chaptersUi.map(chaptersCommunication)
            }
        }
    }

    fun init() {
        navigator.save(CHAPTERS_SCREEN)
        fetchChapters()
    }

    fun getBookName() = bookCache.read().second
}