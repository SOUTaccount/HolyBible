package com.stebakov.holybible.data.net

import com.google.gson.annotations.SerializedName
import com.stebakov.holybible.core.Abstract
import com.stebakov.holybible.data.BookData

data class BookServerModel(
    @SerializedName("id")
    private val id: Int,
    @SerializedName("name")
    private val name: String
) : Abstract.Object<BookData, BookServerToDataMapper>() {
    override fun map(mapper: BookServerToDataMapper): BookData {
        return mapper.map(id, name)
    }
}