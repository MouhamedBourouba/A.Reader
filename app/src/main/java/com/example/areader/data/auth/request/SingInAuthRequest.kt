package com.example.areader.data.auth.request

data class SingInAuthRequest(
    val usernameOrEmail: String,
    val password: String
)
