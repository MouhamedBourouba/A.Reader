package com.example.areader.data.repository.home

import com.example.areader.data.Dto.AuthDto.UserRespond
import com.example.areader.data.Resource

interface HomeRepository {

    suspend fun getUserDataByName(userName: String): Resource<UserRespond>

}