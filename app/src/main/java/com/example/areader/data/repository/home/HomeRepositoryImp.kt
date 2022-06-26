package com.example.areader.data.repository.home

import android.util.Log
import com.example.areader.data.Dto.AuthDto.UserRespond
import com.example.areader.data.Resource
import com.example.areader.data.api.UserApi
import com.example.areader.utils.Constants.TAG

class HomeRepositoryImp(
    private val userApi: UserApi
) : HomeRepository {


    override suspend fun getUserDataByName(userName: String): Resource<UserRespond> {
        return try {
            val user = userApi.getUserByName(userName)

            Log.d(TAG, "getUserDataByName: erooro but NOO XDDDDDDDDDDDDDDDDDDDD")

            Resource.Success(
                UserRespond(
                    userEmail = user.userEmail,
                    userName = user.userName,
                    userBooks = user.userBooks
                )
            )
        } catch (e: Exception) {
            Log.d(TAG, "getUserDataByName: erooro,     $e")
            Resource.Failed(e.message)
        }
    }
}