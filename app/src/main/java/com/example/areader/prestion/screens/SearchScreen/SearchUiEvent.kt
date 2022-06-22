package com.example.areader.prestion.screens.SearchScreen

sealed class SearchUiEvent {
    data class SearchTextChanged(val text: String): SearchUiEvent()
}