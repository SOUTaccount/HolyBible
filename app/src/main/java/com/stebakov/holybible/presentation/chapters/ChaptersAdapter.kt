package com.stebakov.holybible.presentation.chapters

import android.view.View
import android.view.ViewGroup
import com.stebakov.holybible.R
import com.stebakov.holybible.core.BaseAdapter
import com.stebakov.holybible.core.BaseViewHolder
import com.stebakov.holybible.core.CustomTextView
import com.stebakov.holybible.core.Retry

class ChaptersAdapter(private val retry: Retry) :
    BaseAdapter<ChapterUi, BaseViewHolder<ChapterUi>>() {

    override fun getItemViewType(position: Int) = when (list[position]) {
        is ChapterUi.Base -> 0
        is ChapterUi.Fail -> 1
        is ChapterUi.Progress -> 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ChapterUi> {
        return when (viewType) {
            0 -> ChapterViewHolder.Base(R.layout.book_layout.makeView(parent))
            1 -> BaseViewHolder.Fail(R.layout.fail_fullscreen.makeView(parent), retry)
            else -> BaseViewHolder.FullscreenProgress(R.layout.progress_fullscreen.makeView(parent))
        }
    }

    abstract class ChapterViewHolder(view: View) : BaseViewHolder<ChapterUi>(view) {

        class Base(view: View) : ChapterViewHolder(view) {
            private val textView = itemView.findViewById<CustomTextView>(R.id.textView)
            override fun bind(item: ChapterUi) = item.map(textView)
        }
    }
}