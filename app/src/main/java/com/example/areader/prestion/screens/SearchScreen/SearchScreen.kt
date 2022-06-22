package com.example.areader.prestion.screens.SearchScreen

import android.annotation.SuppressLint
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.areader.prestion.screens.ShowTitle
import com.example.areader.prestion.theme.AReaderTheme


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchScreen() {
    AReaderTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        ShowTitle(title = "Search")
                    },
                    navigationIcon = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = null)
                        }
                    },
                    backgroundColor = Color.White
                )
            },
            backgroundColor = MaterialTheme.colors.background
        ) {

        }


    }
}