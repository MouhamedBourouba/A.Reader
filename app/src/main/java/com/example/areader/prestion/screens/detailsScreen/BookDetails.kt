package com.example.areader.prestion.screens.detailsScreen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.areader.R
import com.example.areader.data.Dto.GoogleBooksDto.Item
import com.example.areader.prestion.components.*
import com.example.areader.prestion.screens.destinations.HomeScreenDestination
import com.example.areader.prestion.screens.destinations.SearchScreenDestination
import com.example.areader.prestion.theme.AReaderTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun BookDetailsScreen(
    navController: DestinationsNavigator,
    bookUrl: String?,
    viewModel: BookDetailScreenViewModel = hiltViewModel(),
) {
    viewModel.getBookData(bookUrl ?: "")

    val exception = rememberSaveable() {
        mutableStateOf(false)
    }

    AReaderTheme {
        LaunchedEffect(key1 = viewModel) {
            viewModel.exception.collect {
                exception.value = true
            }
        }

        if (!exception.value) {
            if (viewModel.loadingBookData.value) {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator()
                }
            } else {
                ContentHome(navController, viewModel.currentBook.value!!, viewModel)
            }
        } else {
            NoInternet {
                viewModel.getBookData(bookUrl ?: "")
                exception.value = false
            }
        }
        if (viewModel.loadingSavingBook.value == true) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.LightGray.copy(0.8f)), contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else if (viewModel.loadingSavingBook.value == false) {
            navController.navigate(HomeScreenDestination)
        }


    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ContentHome(
    navController: DestinationsNavigator,
    book: Item,
    viewModel: BookDetailScreenViewModel
) {

    val imageUrl = try {
        book.volumeInfo.imageLinks.thumbnail
    } catch (e: Exception) {
        null
    }

    AReaderTheme {
        Scaffold(
            modifier = Modifier,
            backgroundColor = MaterialTheme.colors.background,
            topBar = {
                TopAppBar(
                    title = {
                        AppBarTitle(title = "book Details")
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.navigate(SearchScreenDestination)
                        }) {
                            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                        }
                    },
                    backgroundColor = Color.White
                )
            }) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(4.dp),
                verticalArrangement = Arrangement.Top
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.Top
                ) {
                    Box(
                        modifier = Modifier
                            .height(200.dp)
                            .width(130.dp)
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .padding(top = 5.dp)
                                .fillMaxWidth(1f)
                                .fillMaxHeight(1f),
                            contentScale = ContentScale.Crop,
                            model = imageUrl,
                            fallback = painterResource(id = R.drawable.no_book_cover_available),
                            placeholder = painterResource(id = R.drawable.loading_image),
                            contentDescription = null
                        )

                    }
                    Spacer(modifier = Modifier.width(8.dp))

                    Column(verticalArrangement = Arrangement.Top) {
                        ShowBootTitle(
                            bookTitle = book.volumeInfo.title,
                            maxLines = 5,
                            titleTextSize = 24
                        )
                        ShowText(
                            author = "Authors: " + (book.volumeInfo.authors.toString()
                                .ifBlank { "No Data" }), 16
                        )
                        ShowText(author = "publisher: " + book.volumeInfo.publisher, 16)
                        ShowText(
                            author = "publishedDate: " + if (book.volumeInfo.publishedDate.isNullOrBlank()) {
                                "No Data"
                            } else book.volumeInfo.publishedDate, 16
                        )
                        ShowText(
                            author = "language: " + book.volumeInfo.language.ifBlank { "No Data" },
                            16
                        )
                        ShowText(
                            author = "subtitle: " + (book.volumeInfo.subtitle ?: "No Data"),
                            16
                        )
                        ShowText(author = "pages: " + (book.volumeInfo.pageCount ?: "No Data"), 16)
                        ShowText(
                            author = "categories: " + (book.volumeInfo.categories ?: "No Data"), 14
                        )
                    }
                }
                val description = book.volumeInfo.description ?: ""
                Log.d("qq", "ContentHome: $description")
                if (description.isNotBlank()) {
                    Surface(
                        modifier = Modifier
                            .padding(horizontal = 15.dp)
                            .height(200.dp)
                            .fillMaxWidth(),
                        color = Color.White,
                        border = BorderStroke(1.dp, color = MaterialTheme.colors.primary)
                    ) {
                        Column(
                            modifier = Modifier
                                .verticalScroll(rememberScrollState())
                        ) {
                            Text(
                                text = description,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                            )
                        }
                    }

                }

                Spacer(modifier = Modifier.padding(top = 16.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RoundedButton("SAVE", 90) {
                        viewModel.saveBook()
                    }

                    RoundedButton("Cancel", 90) {
                        navController.navigate(HomeScreenDestination)
                    }
                }

                Spacer(modifier = Modifier.padding(bottom = 16.dp))
            }
        }
    }
}
