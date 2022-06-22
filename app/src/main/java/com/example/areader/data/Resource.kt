package com.example.areader.data

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    data class Success<T>(val successData: T? = null) : Resource<T>(data = successData)
    data class Failed<T>(val failedMessage: T? = null) : Resource<T>(data = failedMessage)
}
