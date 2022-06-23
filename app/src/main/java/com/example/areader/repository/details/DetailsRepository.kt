package com.example.areader.repository.details

import com.example.areader.data.Dto.GoogleBooksDto.Item
import com.example.areader.data.Resource

interface DetailsRepository {
    suspend fun getCurrentBookData(bookId: String): Resource<Item>
}