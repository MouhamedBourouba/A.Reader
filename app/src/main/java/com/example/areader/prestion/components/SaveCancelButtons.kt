package com.example.areader.prestion.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun SaveCancelButtons(
    isSaveEnabled: Boolean = true,
    cancelText: String = "Cancel",
    onCancel: () -> Unit,
    onSave: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RoundedButton("SAVE", 90, isSaveEnabled) {
            onSave()
        }

        RoundedButton(cancelText, 90) {
            onCancel()
        }
    }
}