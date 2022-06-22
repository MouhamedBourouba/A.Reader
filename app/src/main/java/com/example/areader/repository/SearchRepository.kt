package com.example.areader.repository

interface SearchRepository {
    suspend fun preformSearch(q: String)
}