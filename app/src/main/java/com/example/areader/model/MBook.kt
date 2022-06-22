package com.example.areader.model

data class MBook(
    val id: Int ,
    val isLiked: Boolean,
    val title: String,
    val authors: String? = null,
    val isReading: Boolean = false,
    val rate: Double = 0.0,
    val imageUrl: String = "https://books.google.com/books/content?id=77RZAAAAYAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api"
)