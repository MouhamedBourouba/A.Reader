package com.example.areader.prestion.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.areader.prestion.theme.AReaderTheme

@Composable
fun NoInternet(
    onClick: () -> Unit
) {
    AReaderTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Filled.WifiOff,
                contentDescription = null,
                modifier = Modifier.size(120.dp),
                tint = Color.LightGray
            )
            Spacer(modifier = Modifier.padding(top = 16.dp))
            Text(text = "Please Check Your Internet")
            Spacer(modifier = Modifier.padding(top = 6.dp))
            StandardButton(buttonText = "Try Again", isEnabled = true) {
                onClick()
            }
        }
    }
}