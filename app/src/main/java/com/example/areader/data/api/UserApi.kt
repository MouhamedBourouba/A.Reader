package com.example.areader.data.api

import com.example.areader.data.Dto.AuthDto.UserRespond
import com.example.areader.model.MBook
import retrofit2.http.Body
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserApi {
    @PUT("getUserDataByName/{userName}")
    suspend fun getUserByName(@Path("userName") userName: String): UserRespond

    @PUT("userBooks/update/{userName}")
    suspend fun addBook(
        @Path("userName") userName: String,
        @Body book: MBook
    ): Boolean

    @PUT("userBooks/updateBook/{userName}/{bookId}")
    suspend fun updateBook(@Path("userName") userName: String, @Path("bookId") bookId: String, @Body mBook: MBook)

}