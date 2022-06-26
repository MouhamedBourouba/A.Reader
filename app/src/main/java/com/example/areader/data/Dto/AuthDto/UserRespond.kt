package com.example.areader.data.Dto.AuthDto

import com.example.areader.model.MBook

data class UserRespond(
    val userEmail: String? = null,
    val userName: String? = null,
    val userBooks: ArrayList<MBook>? = null
)