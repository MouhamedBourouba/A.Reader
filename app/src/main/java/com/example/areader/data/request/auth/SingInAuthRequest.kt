package com.example.areader.data.request.auth

data class SingInAuthRequest(
    val usernameOrEmail: String,
    val password: String
)
