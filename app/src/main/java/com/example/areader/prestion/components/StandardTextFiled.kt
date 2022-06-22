package com.example.areader.prestion.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.areader.prestion.theme.AReaderTheme

@ExperimentalComposeUiApi
@Composable
fun StandardTextFiled(
    value: String,
    onChangeListener: (String) -> Unit,
    placeholder: String,
    leadingIcon: ImageVector,
    isPasswordTextFieldValue: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
) {
    val keyboard = LocalSoftwareKeyboardController.current

    val isVisible = remember {
        mutableStateOf(false)
    }

    AReaderTheme {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            value = value,
            onValueChange = onChangeListener,
            label = { Text(text = placeholder) },
            leadingIcon = { Icon(imageVector = leadingIcon, contentDescription = null, tint = Color.Black.copy(0.8f)) },
            keyboardActions = KeyboardActions(onDone = { keyboard?.hide() }),
            maxLines = 1,
            colors = TextFieldDefaults.textFieldColors(
                focusedLabelColor = MaterialTheme.colors.secondary,
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = MaterialTheme.colors.secondary
            ),
            keyboardOptions =
            if (isPasswordTextFieldValue) KeyboardOptions(keyboardType = KeyboardType.Password)
            else KeyboardOptions(
                keyboardType = keyboardType
            ),
            visualTransformation =
            if (isPasswordTextFieldValue && !isVisible.value) PasswordVisualTransformation()
            else VisualTransformation.None,
            trailingIcon = {
                if (isPasswordTextFieldValue) {

                    if (isVisible.value) {
                        IconButton(onClick = { isVisible.value = !isVisible.value }) {
                            Icon(
                                imageVector = Icons.Outlined.Visibility,
                                contentDescription = null,
                                tint = Color.Black
                            )
                        }
                    } else IconButton(onClick = {
                        isVisible.value = !isVisible.value
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.VisibilityOff,
                            contentDescription = null,
                            tint = Color.Black
                        )
                    }
                }
            }
        )
    }
}