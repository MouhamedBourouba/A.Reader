package com.example.areader.repository

import com.example.areader.data.auth.AuthResult
import com.example.areader.data.auth.request.SingInAuthRequest
import com.example.areader.data.auth.request.SingUpAuthRequest
import com.example.areader.data.auth.request.TokenRequest
import com.example.areader.data.auth.response.SingInResponse
import com.example.areader.data.auth.response.SingUpResponse
import com.example.areader.data.auth.response.UserResponse

interface Repository {
    suspend fun singUp(singUpAuthRequest: SingUpAuthRequest): AuthResult<SingUpResponse>
    suspend fun singIn(singInAuthRequest: SingInAuthRequest): AuthResult<SingInResponse>
    suspend fun getUser(tokenRequest: TokenRequest): AuthResult<UserResponse>
    suspend fun isAuthenticate(): Boolean
}