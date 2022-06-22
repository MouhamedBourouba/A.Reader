package com.example.areader.data.api

import retrofit2.http.GET
import retrofit2.http.Query

interface BooksApi {
    @GET("volumes")
    fun BookSearch(
        @Query("q") q: String = "search"
    )
}