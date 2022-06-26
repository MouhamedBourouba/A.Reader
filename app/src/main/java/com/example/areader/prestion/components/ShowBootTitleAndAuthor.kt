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
import androidx.compose.ui.unit.sp
import com.example.areader.R


@Composable
fun ShowBootTitle(bookTitle: String, titleTextSize: Int? = null, maxLines: Int? = null) {
    Text(
        modifier = Modifier.padding(start = 15.dp),
        text = bookTitle,
        maxLines = maxLines ?: 2,
        style = if (titleTextSize == null)
            MaterialTheme.typography.subtitle2.copy(
                fontWeight = FontWeight.Bold,
                fontFamily = Font(R.font.joan_regular).toFontFamily()
            )
        else
            MaterialTheme.typography.subtitle2.copy(
                fontSize = titleTextSize.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = Font(R.font.joan_regular).toFontFamily()
            ),
        overflow = TextOverflow.Ellipsis
    )
}


@Composable
fun ShowText(author: String, textSize: Int? = null, maxLines: Int? = null) {
    Text(
        modifier = Modifier.padding(start = 15.dp),
        text = author,
        maxLines = maxLines ?: 100,
        style =
        if (textSize != null)
            MaterialTheme.typography.caption.copy(fontSize = textSize.sp)
        else
            MaterialTheme.typography.caption,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun ShowBookTitleAndAuthor(bookTitle: String, author: String, titleTextSize: Int? = null, authorsTextSize: Int? = null) {
    Column {
        ShowBootTitle(bookTitle = bookTitle, titleTextSize)
        ShowText(author = author, authorsTextSize, maxLines = 1)
    }
}

