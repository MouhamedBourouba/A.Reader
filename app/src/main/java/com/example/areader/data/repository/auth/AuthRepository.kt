package com.example.areader.data.repository.auth

import com.example.areader.data.AuthResult
import com.example.areader.data.Dto.AuthDto.SingInResponse
import com.example.areader.data.Dto.AuthDto.SingUpResponse
import com.example.areader.data.request.auth.SingInAuthRequest
import com.example.areader.data.request.auth.SingUpAuthRequest

interface AuthRepository {
    suspend fun singUp(singUpAuthRequest: SingUpAuthRequest): AuthResult<SingUpResponse>
    suspend fun singIn(singInAuthRequest: SingInAuthRequest): AuthResult<SingInResponse>
    suspend fun isAuthenticate(): Boolean
}