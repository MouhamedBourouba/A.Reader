package com.example.areader.data.repository.updateBooks

import com.example.areader.data.Resource
import com.example.areader.data.api.UserApi
import com.example.areader.model.MBook

class UpdateRepositoryImp(
    private val userApi: UserApi
): UpdateRepository {
    override suspend fun updateBook(userName: String, bookId: String, mBook: MBook): Resource<Unit> {
        userApi.updateBook(userName, bookId, mBook)
        return Resource.Success()
    }
}