package com.example.areader.repository.auth

import com.example.areader.data.AuthResult
import com.example.areader.data.request.auth.SingInAuthRequest
import com.example.areader.data.request.auth.SingUpAuthRequest
import com.example.areader.data.request.auth.TokenRequest
import com.example.areader.data.Dto.AuthDto.SingInResponse
import com.example.areader.data.Dto.AuthDto.SingUpResponse
import com.example.areader.data.Dto.AuthDto.UserResponse

interface AuthRepository {
    suspend fun singUp(singUpAuthRequest: SingUpAuthRequest): AuthResult<SingUpResponse>
    suspend fun singIn(singInAuthRequest: SingInAuthRequest): AuthResult<SingInResponse>
    suspend fun getUser(tokenRequest: TokenRequest): AuthResult<UserResponse>
    suspend fun isAuthenticate(): Boolean
}