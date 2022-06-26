package com.example.areader.data.repository.details

import com.example.areader.data.Dto.GoogleBooksDto.Item
import com.example.areader.data.Resource
import com.example.areader.data.api.BooksApi
import com.example.areader.data.api.UserApi
import com.example.areader.model.MBook

class DetailsRepositoryImp(
    private val booksApi: BooksApi,
    private val userApi: UserApi
) : DetailsRepository {
    override suspend fun getCurrentBookData(bookId: String): Resource<Item> {
        return try {
            Resource.Success(booksApi.bookInfo(bookId))
        } catch (e: Exception) {
            Resource.Failed(e.message)
        }
    }

    override suspend fun addBookToUser(userName: String,mBook: MBook): Resource<Unit> {
        return try {
            val updater = userApi.addBook(userName, mBook)
            if (updater) {
                Resource.Success()
            } else {
                Resource.Failed()
            }
        } catch (e: Exception) {
            Resource.Success()
        }
    }
}