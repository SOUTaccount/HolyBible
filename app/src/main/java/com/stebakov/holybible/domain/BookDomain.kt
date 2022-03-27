package com.stebakov.holybible.domain

import com.stebakov.holybible.core.Abstract
import com.stebakov.holybible.data.net.BookServerModel
import com.stebakov.holybible.presentation.BookUi

sealed class BookDomain : Abstract.Object<BookUi, Abstract.Mapper.Empty>() {
    class Success : BookDomain() {
        override fun map(mapper: Abstract.Mapper.Empty): BookUi {
            TODO("Not yet implemented")
        }

    }

    class Fail(errorType: Int) : BookDomain() {
        override fun map(mapper: Abstract.Mapper.Empty): BookUi {
            TODO("Not yet implemented")
        }

    }
}