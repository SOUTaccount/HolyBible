package com.stebakov.holybible.data.net

import com.google.gson.annotations.SerializedName
import com.stebakov.holybible.core.Abstract
import com.stebakov.holybible.data.BookData
import com.stebakov.holybible.data.ToBookMapper

data class BookCloud(
    @SerializedName("id")
    private val id: Int,
    @SerializedName("name")
    private val name: String
) : Abstract.Object<BookData, ToBookMapper> {
    override fun map(mapper: ToBookMapper) = mapper.map(id, name)
}