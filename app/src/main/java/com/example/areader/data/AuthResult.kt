package com.example.areader.data


sealed class AuthResult<T>(val data: T? = null, val message: String? = null) {
    class Authorized<T>(data: T? = null) : AuthResult<T>(data)
    class UnAuthorized<T>(error: String? = null) : AuthResult<T>(message = error)
    class UnknownError<T>(error: String? = null) : AuthResult<T>(message = error)
}
