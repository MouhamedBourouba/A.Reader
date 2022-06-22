package com.example.areader.prestion.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun ShowBookImage(imageUrl: String) {
    AsyncImage(
        modifier = Modifier
            .height(140.dp)
            .width(100.dp),
        model = imageUrl,
        contentDescription = null
    )
}

