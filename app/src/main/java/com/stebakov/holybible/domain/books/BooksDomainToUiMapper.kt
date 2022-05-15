package com.stebakov.holybible.domain.books

import com.stebakov.holybible.core.Abstract
import com.stebakov.holybible.core.ResourceProvider
import com.stebakov.holybible.presentation.books.BooksUi

abstract class BooksDomainToUiMapper(resourceProvider: ResourceProvider) :
    Abstract.Mapper.DomainToUi.Base<List<BookDomain>, BooksUi>(resourceProvider)