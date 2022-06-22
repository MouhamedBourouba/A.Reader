package com.example.areader.data.Dto.GoogleBooksDto

data class BookModel(
    val items: List<Item>,
    val kind: String,
    val totalItems: Int
)