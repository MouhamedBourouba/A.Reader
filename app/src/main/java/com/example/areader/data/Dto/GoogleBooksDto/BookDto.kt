package com.example.areader.data.Dto.GoogleBooksDto

data class BookDto(
    val items: List<Item>,
    val kind: String,
    val totalItems: Int
)