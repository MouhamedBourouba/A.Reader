package com.example.areader.data.repository.details

import com.example.areader.data.Dto.GoogleBooksDto.Item
import com.example.areader.data.Resource
import com.example.areader.model.MBook

interface DetailsRepository {
    suspend fun getCurrentBookData(bookId: String): Resource<Item>
    suspend fun addBookToUser(userName: String,mBook: MBook): Resource<Unit>
}