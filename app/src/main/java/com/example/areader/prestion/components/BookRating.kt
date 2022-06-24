package com.example.areader.prestion.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.StarRate
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BookRating(
    bookRating: String,
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(end = 6.dp, top = 8.dp)
    ) {
        Surface(
            modifier = Modifier
                .height(90.dp),
            shape = RoundedCornerShape(37.dp),
            elevation = 6.dp,
            border = BorderStroke(1.dp, color = Color.Black)
        ) {
            Column(
                modifier = Modifier
                    .padding(bottom = 4.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Rounded.StarRate,
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                }
                Text(text = bookRating, style = MaterialTheme.typography.subtitle1)
            }
        }
    }

}