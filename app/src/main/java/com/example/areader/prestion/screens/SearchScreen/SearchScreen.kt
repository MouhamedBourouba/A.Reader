package com.example.areader.prestion.screens.SearchScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Book
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.areader.R
import com.example.areader.data.Dto.GoogleBooksDto.Item
import com.example.areader.prestion.components.AppBarTitle
import com.example.areader.prestion.components.ShowAuthor
import com.example.areader.prestion.components.ShowBookTitleAndAuthor
import com.example.areader.prestion.components.StandardTextFiled
import com.example.areader.prestion.theme.AReaderTheme
import com.example.areader.utils.Screens


@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchScreen(navController: NavController, viewModel: SearchScreenViewModel = hiltViewModel()) {

    val books = viewModel.bookData?.items?.toMutableList() ?: emptyList()

    AReaderTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        AppBarTitle("Search Books")
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.navigate(Screens.Home.route)
                        }) {
                            Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = null)
                        }
                    },
                    backgroundColor = Color.White
                )
            },
            backgroundColor = MaterialTheme.colors.background
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                StandardTextFiled(
                    value = viewModel.searchText,
                    onChangeListener = {
                        viewModel.onEvent(SearchUiEvent.SearchTextChanged(it))
                    },
                    placeholder = "Search",
                    leadingIcon = Icons.Outlined.Book
                )

                Spacer(modifier = Modifier.padding(bottom = 16.dp))

                if (viewModel.loading) {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator()
                    }
                } else {
                    LazyColumn {
                        items(books) {
                            BookItem(it) { bookUrl ->
                                navController.navigate("details_screen/$bookUrl")
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BookItem(
    book: Item,
    onClick: (String) -> Unit
) {
    val image: String? = try {
        book.volumeInfo.imageLinks.thumbnail ?: book.volumeInfo.imageLinks.smallThumbnail
    } catch (e: NullPointerException) {
        null
    }
    val author = book.volumeInfo.authors?.first() ?: "no data"
    val publishDate = book.volumeInfo.publishedDate ?: "no data"

    Surface(
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .clickable { onClick.invoke(book.selfLink) },
        color = Color.White,
        elevation = 6.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            if (image != null) {
                AsyncImage(
                    modifier = Modifier
                        .width(120.dp)
                        .height(100.dp),
                    model = image,
                    placeholder = painterResource(id = R.drawable.loading_image),
                    contentDescription = null
                )
            } else Image(
                modifier = Modifier
                    .width(120.dp)
                    .height(100.dp),
                painter = painterResource(id = R.drawable.no_book_cover_available),
                contentDescription = null
            )
            Column {
                ShowBookTitleAndAuthor(
                    bookTitle = book.volumeInfo.title,
                    "Author: $author"
                )
                ShowAuthor(author = "publish date: $publishDate")

            }
        }


    }
}


