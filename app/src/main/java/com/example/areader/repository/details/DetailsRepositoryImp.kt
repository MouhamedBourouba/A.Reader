package com.example.areader.repository.details

import com.example.areader.data.Dto.GoogleBooksDto.Item
import com.example.areader.data.Resource
import com.example.areader.data.api.BooksApi

class DetailsRepositoryImp(
    private val api: BooksApi
) : DetailsRepository {
    override suspend fun getCurrentBookData(bookId: String): Resource<Item> {
        return try {
            Resource.Success(api.bookInfo(bookId))
        } catch (e: Exception) {
            Resource.Failed(e.message)
        }
    }
}