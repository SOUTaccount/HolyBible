package com.stebakov.holybible.data.books

import com.stebakov.holybible.core.Abstract
import com.stebakov.holybible.domain.books.BooksDomain

abstract class BooksDataToDomainMapper : Abstract.Mapper.DataToDomain.Base<List<BookData>, BooksDomain>()