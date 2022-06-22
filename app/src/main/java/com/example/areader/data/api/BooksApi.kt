package com.example.areader.data.api

import com.example.areader.data.Dto.GoogleBooksDto.BookDto
import retrofit2.http.GET
import retrofit2.http.Query

interface BooksApi {
    @GET("volumes")
    suspend fun bookSearch(
        @Query("q") q: String = "search"
    ): BookDto
}