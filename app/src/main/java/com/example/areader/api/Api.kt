package com.example.areader.api

import com.example.areader.data.auth.request.SingInAuthRequest
import com.example.areader.data.auth.request.SingUpAuthRequest
import com.example.areader.data.auth.response.ErrorRespond
import com.example.areader.data.auth.response.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface Api {
    @POST("singUp")
    suspend fun singUp(
        @Body body: SingUpAuthRequest
    ): Unit

    @POST("singIn")
    suspend fun singIn(
        @Body body: SingInAuthRequest
    ): String

    @GET("Authenticate")
    suspend fun authenticate(
        @Header("Authorization") token: String
    ): Unit

    @GET("getUser")
    suspend fun getUser(
        @Header("Authorization") token: String
    ): UserResponse


    @POST("singIn")
    suspend fun getError(@Body body: SingInAuthRequest): ErrorRespond

}