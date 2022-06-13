package com.example.areader.model.ApiModels

data class BookModel(
    val items: List<Item>,
    val kind: String,
    val totalItems: Int
)