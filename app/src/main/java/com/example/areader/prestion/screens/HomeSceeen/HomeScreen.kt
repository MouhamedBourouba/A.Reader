package com.example.areader.prestion.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.areader.R
import com.example.areader.model.MBook
import com.example.areader.prestion.components.*
import com.example.areader.prestion.screens.HomeSceeen.HomeScreenUiEvent
import com.example.areader.prestion.screens.HomeSceeen.HomeScreenViewModel
import com.example.areader.prestion.screens.destinations.AuthScreenDestination
import com.example.areader.prestion.screens.destinations.SearchScreenDestination
import com.example.areader.prestion.theme.AReaderTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Destination
@Composable
fun HomeScreen(
    navController: DestinationsNavigator,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = viewModel, block = {
        viewModel.singOuChannel.collect {
            navController.popBackStack()
            navController.navigate(AuthScreenDestination)
        }
    })

    AReaderTheme {
//        if (viewModel.loading.value) Box(
//            Modifier.fillMaxSize(),
//            contentAlignment = Alignment.Center
//        ) {
//            CircularProgressIndicator()

//        } else

        Home(viewModel = viewModel, navController = navController)
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Home(viewModel: HomeScreenViewModel, navController: DestinationsNavigator) {
    AReaderTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    backgroundColor = Color.White,
                    elevation = 4.dp
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(horizontal = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                modifier = Modifier
                                    .size(35.dp)
                                    .padding(end = 8.dp),
                                painter = painterResource(id = R.drawable.book),
                                contentDescription = null,
                                tint = Color.Unspecified,
                            )
                            AppBarTitle("A.Reader")

                        }

                        IconButton(onClick = {
                            viewModel.onEvent(HomeScreenUiEvent.SingOut)
                        }) {
                            Icon(
                                modifier = Modifier
                                    .size(30.dp),
                                imageVector = Icons.Outlined.Logout,
                                contentDescription = null,
                                tint = MaterialTheme.colors.secondary
                            )
                        }
                    }
                }
            },
            backgroundColor = MaterialTheme.colors.background,
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        navController.navigate(SearchScreenDestination)
                    },
                    backgroundColor = MaterialTheme.colors.primary
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Add, contentDescription = null
                    )
                }
            }
        ) {

            HomeContent(viewModel)

        }
    }
}

@Composable
fun HomeContent(viewModel: HomeScreenViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp, start = 6.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
    ) {
        MainContent(viewModel = viewModel)
    }
}

@Composable
fun MainContent(
    viewModel: HomeScreenViewModel
) {
    Column(
        modifier = Modifier
            .padding(start = 8.dp, bottom = 24.dp)
            .verticalScroll(rememberScrollState())
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.Start
    ) {

        // Show Account And Title
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ShowTitle(title = "Your Reading \n  Activity Right Now ...")

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    modifier = Modifier.size(45.dp),
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = null,
                    tint = MaterialTheme.colors.primary
                )
                Text(
                    modifier = Modifier
                        .width(100.dp),
                    textAlign = TextAlign.Center,
                    text = viewModel.currentUser.value.userName ?: "",
                    style = MaterialTheme.typography.overline,
                    color = MaterialTheme.colors.error,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
            }
        }


        Spacer(modifier = Modifier.padding(top = 8.dp))


        if (viewModel.readingList.isNotEmpty()) LazyRow() {
            items(viewModel.readingList) {
                BookCard(it)
                Spacer(modifier = Modifier.width(16.dp))
            }
        }
        else Box(
            modifier = Modifier
                .height(242.dp)
                .width(202.dp),
            contentAlignment = Alignment.TopEnd
        ) {

            Text(
                modifier = Modifier.padding(top = 24.dp),
                text = "No Books Found, add Book",
                style = MaterialTheme.typography.caption.copy(
                    color = MaterialTheme.colors.error.copy(
                        0.7f
                    )
                )
            )

        }

        Spacer(modifier = Modifier.height(8.dp))

        ShowTitle(title = "Pending List")

        Spacer(modifier = Modifier.height(8.dp))

        if (viewModel.pendingList.isNotEmpty()) LazyRow() {
            items(viewModel.pendingList) {
                BookCard(it)
                Spacer(modifier = Modifier.width(16.dp))
            }
        } else Box(
            modifier = Modifier
                .height(242.dp)
                .width(202.dp),
            contentAlignment = Alignment.TopEnd
        ) {

            Text(
                modifier = Modifier.padding(top = 24.dp),
                text = "No Books Found, add Book",
                style = MaterialTheme.typography.caption.copy(
                    color = MaterialTheme.colors.error.copy(
                        0.7f
                    )
                )
            )

        }

    }
}


@Composable
fun BookCard(
    book: MBook,
    onPressed: (String?) -> Unit = {}
) {
    val context = LocalContext.current
    val resources = context.resources
    val displayMatrix = resources.displayMetrics
    val screenWidth = displayMatrix.widthPixels / displayMatrix.density

    AReaderTheme {
        Card(
            modifier = Modifier
                .height(242.dp)
                .width(202.dp)
                .clickable { onPressed.invoke(book.googleBookApiId ?: "") },
            shape = RoundedCornerShape(29.dp),
            backgroundColor = Color.White
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .width((screenWidth - 20).dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    ShowBookImage(imageUrl = book.imageUrl!!)
                    BookRating(book.rate.toString())
                }

                ShowBookTitleAndAuthor(book.title!!, author = book.authors.toString())

                Row(
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    RoundedButton()
                }
            }
        }
    }

}


@Composable
fun ShowTitle(title: String) {
    Text(
        text = title,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
    )
}



