package com.example.areader.data.repository.updateBooks

import com.example.areader.data.Resource
import com.example.areader.model.MBook

interface UpdateRepository  {

    suspend fun updateBook(userName: String, bookId: String, mBook: MBook): Resource<Unit>

}