package com.example.areader.prestion.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp

@Composable
fun AppBarTitle(title: String) {
    Text(
        text = title,
        color = MaterialTheme.colors.primary,
        fontSize = 19.sp
    )
}