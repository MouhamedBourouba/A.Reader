package com.example.areader.repository.search

import android.util.Log
import com.example.areader.data.Dto.GoogleBooksDto.BookDto
import com.example.areader.data.Resource
import com.example.areader.data.api.BooksApi

class SearchRepositoryImp(
    private val booksApi: BooksApi
) : SearchRepository {
    override suspend fun preformSearch(q: String): Resource<BookDto> {
        return try {
            val books = booksApi.bookSearch(q)
            Log.d("TAG", "preformSearch: ${books.items?.first() ?: ""}")
            Resource.Success(successData = books)

        } catch (e: Exception) {
            Resource.Failed()
        }
    }
}