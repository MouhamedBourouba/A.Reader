package com.example.areader.data.api

import com.example.areader.data.Dto.GoogleBooksDto.BookDto
import com.example.areader.data.Dto.GoogleBooksDto.Item
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BooksApi {
    @GET("volumes")
    suspend fun bookSearch(
        @Query("q") q: String = "search"
    ): BookDto

    @GET("volumes/{book}")
    suspend fun bookInfo(@Path("book") bookId: String): Item
}