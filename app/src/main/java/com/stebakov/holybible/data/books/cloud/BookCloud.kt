package com.stebakov.holybible.data.books.cloud

import com.google.gson.annotations.SerializedName
import com.stebakov.holybible.core.Abstract
import com.stebakov.holybible.data.books.BookData
import com.stebakov.holybible.data.books.ToBookMapper

data class BookCloud(
    @SerializedName("id")
    private val id: Int,
    @SerializedName("name")
    private val name: String,
    @SerializedName("testament")
    private val testament: String
) : Abstract.Object<BookData, ToBookMapper> {
    override fun map(mapper: ToBookMapper) = mapper.map(id, name, testament)
}