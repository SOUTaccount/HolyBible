package com.stebakov.holybible.presentation.chapters

import com.stebakov.holybible.core.Communication

interface ChaptersCommunication : Communication<List<ChapterUi>> {

    class Base : Communication.Base<List<ChapterUi>>(), ChaptersCommunication
}