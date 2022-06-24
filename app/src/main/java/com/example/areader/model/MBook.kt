package com.example.areader.model

data class MBook(
    val title: String? = null,
    val authors: String? = null,
    val isReading: Boolean? = false,
    val startReading: Long? = null,
    val rate: Double? = null,
    val description: String? = null,
    val imageUrl: String? = null,
    val finishedReading: Long? = null,
    val googleBookApiId: String? = null
)