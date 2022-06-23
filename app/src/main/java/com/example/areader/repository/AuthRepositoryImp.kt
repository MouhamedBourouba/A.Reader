package com.example.areader.repository

import android.content.SharedPreferences
import android.util.Log
import com.example.areader.data.api.AuthApi
import com.example.areader.data.AuthResult
import com.example.areader.data.request.auth.SingInAuthRequest
import com.example.areader.data.request.auth.SingUpAuthRequest
import com.example.areader.data.request.auth.TokenRequest
import com.example.areader.data.Dto.AuthDto.SingInResponse
import com.example.areader.data.Dto.AuthDto.SingUpResponse
import com.example.areader.data.Dto.AuthDto.UserResponse
import com.example.areader.utils.Constants.TAG
import retrofit2.HttpException

class AuthRepositoryImp(private var authApi: AuthApi, private var sharedPreferences: SharedPreferences) :
    AuthRepository {
    override suspend fun singUp(singUpAuthRequest: SingUpAuthRequest): AuthResult<SingUpResponse> {
        return try {

            Log.d(TAG, "singIn: trying to singUp repo")


            authApi.singUp(singUpAuthRequest)

            singIn(SingInAuthRequest(singUpAuthRequest.email, singUpAuthRequest.password))

            Log.d(TAG, "singUp: sinup succses")


            AuthResult.Authorized(SingUpResponse(isSuccess = true))

        } catch (e: HttpException) {
            Log.d(TAG, "singUp: $e")

            AuthResult.UnAuthorized(e.response()?.errorBody()?.string())

        } catch (e: Exception) {
            Log.d(TAG, "singUp: $e")


            AuthResult.UnknownError()

        }
    }

    override suspend fun singIn(singInAuthRequest: SingInAuthRequest): AuthResult<SingInResponse> {
        return try {
            Log.d(TAG, "singIn: trying to login repo")
            Log.d(TAG, "singIn: ${authApi.singIn(singInAuthRequest)}")

            val token = authApi.singIn(singInAuthRequest)

            if (token.isEmpty()) {
                AuthResult.UnAuthorized<SingInResponse>()
            }

            sharedPreferences
                .edit()
                .putString("jwt", token)
                .apply()

            Log.d(TAG, "singIn: $token")


            AuthResult.Authorized(SingInResponse(token))

        } catch (e: HttpException) {

            val error = e.response()?.errorBody()?.string()

            AuthResult.UnAuthorized(error)

        } catch (e: Exception) {
            Log.d(TAG, "singIn: $e")
            AuthResult.UnknownError()

        }
    }

    override suspend fun getUser(tokenRequest: TokenRequest): AuthResult<UserResponse> {
        return try {
            val user = authApi.getUser("Bearer " + tokenRequest.token)

            AuthResult.Authorized(
                UserResponse(
                    user.userEmail,
                    user.userName
                )
            )
        } catch (e: Exception) {
            AuthResult.UnknownError()
        }
    }

    override suspend fun isAuthenticate(): Boolean {
        return try {
            val token  = sharedPreferences.getString("jwt", null) ?: false
            authApi.authenticate("Bearer $token")
            true
        } catch (e: Exception) {
            false
        }
    }
}