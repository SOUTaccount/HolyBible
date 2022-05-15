package com.stebakov.holybible.presentation.books

import com.stebakov.holybible.core.Communication

interface BooksCommunication : Communication<List<BookUi>> {

    class Base : Communication.Base<List<BookUi>>(), BooksCommunication
}