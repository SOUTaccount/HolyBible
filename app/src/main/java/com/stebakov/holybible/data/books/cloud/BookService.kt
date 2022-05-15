package com.stebakov.holybible.data.books.cloud

import okhttp3.ResponseBody
import retrofit2.http.GET

interface BookService {
    @GET("books")
    suspend fun fetchBooks(): ResponseBody
}