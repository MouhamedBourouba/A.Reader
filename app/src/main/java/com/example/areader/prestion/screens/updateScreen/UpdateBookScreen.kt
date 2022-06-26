package com.example.areader.prestion.screens.updateScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.areader.R
import com.example.areader.model.MBook
import com.example.areader.prestion.components.AppBarTitle
import com.example.areader.prestion.components.SaveCancelButtons
import com.example.areader.prestion.components.ShowLoading
import com.example.areader.prestion.screens.ShowTitle
import com.example.areader.prestion.screens.destinations.HomeScreenDestination
import com.example.areader.prestion.theme.AReaderTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun UpdateBookScreen(
    navigator: DestinationsNavigator,
    viewModel: UpdateBookScreenViewModel = hiltViewModel(),
    mBook: MBook
) {
    AReaderTheme {
        if (viewModel.loading.value == null) Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        AppBarTitle(title = "Update ${mBook.title}")
                    },
                    navigationIcon = {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                    },
                    backgroundColor = Color.White
                )
            }
        ) {
            Surface(modifier = Modifier.padding(it), color = MaterialTheme.colors.background) {
                BookRounded(viewModel, navigator, mBook)
            }
        }
        else if (viewModel.loading.value == true) {
            ShowLoading()
        }
    }
}

@Composable
fun BookRounded(viewModel: UpdateBookScreenViewModel,navigator: DestinationsNavigator,mBook: MBook) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .height(200.dp),
        shape = RoundedCornerShape(30.dp),
        elevation = 6.dp,
    ) {
        Column() {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                Row {
                    AsyncImage(
                        model = mBook.imageUrl, contentDescription = null,
                        placeholder = painterResource(
                            id = R.drawable.loading_image
                        ),
                        error = painterResource(id = R.drawable.no_book_cover_available),
                    )
                    ShowTitle(title = mBook.title ?: "")
                }

            }

            SaveCancelButtons(cancelText = "Delete", isSaveEnabled = false ,onCancel = { navigator.navigate(HomeScreenDestination) }) {
                viewModel.updateBook(mBook.copy(isReading = true))
            }
        }
    }
}
