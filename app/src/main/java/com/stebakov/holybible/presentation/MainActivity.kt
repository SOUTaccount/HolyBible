package com.stebakov.holybible.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.stebakov.holybible.R
import com.stebakov.holybible.core.BibleApp
import com.stebakov.holybible.core.Retry
import com.stebakov.holybible.presentation.Screens.Companion.BOOKS_SCREEN
import com.stebakov.holybible.presentation.Screens.Companion.CHAPTERS_SCREEN
import com.stebakov.holybible.presentation.books.BooksAdapter
import com.stebakov.holybible.presentation.books.BooksFragment
import com.stebakov.holybible.presentation.chapters.ChaptersAdapter
import com.stebakov.holybible.presentation.chapters.ChaptersFragment
import java.lang.IllegalStateException

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = (application as BibleApp).mainViewModel

        viewModel.observe(this) {
            val fragment = when (it) {
                BOOKS_SCREEN -> BooksFragment()
                CHAPTERS_SCREEN -> ChaptersFragment()
                else -> throw IllegalStateException("screen id undefined $it")
            }
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit()
        }
        viewModel.init()
    }

    override fun onBackPressed() {
        if (viewModel.navigateBack())
            super.onBackPressed()
    }
}