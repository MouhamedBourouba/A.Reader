package com.example.areader.prestion.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun RoundedButton(
    text: String = "Reading",
    width: Int? = null,
    onPressed: () -> Unit = {},
) {
    Button(
        modifier =
        if (width != null) {
            Modifier
                .height(40.dp)
                .width(width.dp)
        } else Modifier
            .height(40.dp),
        onClick = { onPressed.invoke() },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.secondary,
            contentColor = Color.White
        ),

        shape = RoundedCornerShape(topStart = 29.dp, bottomEnd = 29.dp)
    ) {
        Text(text = text)
    }
}
