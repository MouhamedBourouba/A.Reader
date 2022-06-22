package com.example.areader.prestion.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.toFontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.areader.R


@Composable
fun ShowBootTitle(bookTitle: String) {
    Text(
        modifier = Modifier.padding(start = 15.dp),
        text = bookTitle,
        style = MaterialTheme.typography.subtitle2.copy(
            fontWeight = FontWeight.Bold,
            fontFamily = Font(R.font.joan_regular).toFontFamily()
        ),
        overflow = TextOverflow.Ellipsis
    )
}



@Composable
fun ShowAuthor(author: String) {
    Text(
        modifier = Modifier.padding(start = 15.dp),
        text = author,
        style = MaterialTheme.typography.caption,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun ShowBookTitleAndAuthor(bookTitle: String, author: String) {
    Column {
        ShowBootTitle(bookTitle = bookTitle)
        ShowAuthor(author = author)
    }
}

