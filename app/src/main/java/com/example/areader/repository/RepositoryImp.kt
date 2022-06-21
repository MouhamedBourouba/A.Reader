package com.example.areader.repository

import android.content.SharedPreferences
import android.util.Log
import com.example.areader.api.Api
import com.example.areader.data.auth.AuthResult
import com.example.areader.data.auth.request.SingInAuthRequest
import com.example.areader.data.auth.request.SingUpAuthRequest
import com.example.areader.data.auth.request.TokenRequest
import com.example.areader.data.auth.response.SingInResponse
import com.example.areader.data.auth.response.SingUpResponse
import com.example.areader.data.auth.response.UserResponse
import com.example.areader.utils.Constants.TAG
import retrofit2.HttpException

class RepositoryImp(private var api: Api, private var sharedPreferences: SharedPreferences) :
    Repository {
    override suspend fun singUp(singUpAuthRequest: SingUpAuthRequest): AuthResult<SingUpResponse> {
        return try {

            Log.d(TAG, "singIn: trying to singUp repo")


            api.singUp(singUpAuthRequest)

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
            Log.d(TAG, "singIn: ${api.singIn(singInAuthRequest)}")

            val token = api.singIn(singInAuthRequest)

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

            AuthResult.UnknownError()

        }
    }

    override suspend fun getUser(tokenRequest: TokenRequest): AuthResult<UserResponse> {
        return try {
            val user = api.getUser("Bearer " + tokenRequest.token)

            AuthResult.Authorized(
                UserResponse(
                    user.username,
                    user.email
                )
            )
        } catch (e: Exception) {
            AuthResult.UnknownError()
        }
    }

    override suspend fun isAuthenticate(): Boolean {
        return try {
            if (sharedPreferences.getString("jwt", null) != null) {
                try {
                    true
                } catch (e: Exception) {
                    true
                }
            } else {
                val tokenFromPreferences =
                    sharedPreferences.getString("jwt", null) ?: AuthResult.UnAuthorized<Unit>()

                api.authenticate("Bearer $tokenFromPreferences")
                true
            }

        } catch (e: Exception) {

            false

        }
    }
}