package com.example.areader.prestion.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.areader.prestion.theme.AReaderTheme

@Composable
fun ShowLoading(loadingText: String = "Loading...") {
    AReaderTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.LightGray.copy(alpha = 0.3f),
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Surface(
                    modifier = Modifier
                        .width(300.dp)
                        .height(100.dp),
                    shape = RoundedCornerShape(15.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 24.dp)
                            .background(color = Color.White)
                            .clip(RoundedCornerShape(15.dp)),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                    ) {

                        CircularProgressIndicator()
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(text = loadingText)
                    }
                }
            }
        }
    }
}
