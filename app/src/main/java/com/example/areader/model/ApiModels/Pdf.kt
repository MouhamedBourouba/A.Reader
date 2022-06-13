package com.example.areader.model.ApiModels

data class Pdf(
    val acsTokenLink: String,
    val downloadLink: String,
    val isAvailable: Boolean
)