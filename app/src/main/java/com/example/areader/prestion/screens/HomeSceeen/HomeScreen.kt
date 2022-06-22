package com.example.areader.prestion.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.StarBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.toFontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.areader.R
import com.example.areader.model.MBook
import com.example.areader.prestion.screens.HomeSceeen.HomeScreenUiEvent
import com.example.areader.prestion.screens.HomeSceeen.HomeScreenViewModel
import com.example.areader.prestion.theme.AReaderTheme
import com.example.areader.utils.Screens

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel(),
    navController: NavController = rememberNavController()
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = viewModel, block = {
        viewModel.singOuChannel.collect {
            navController.popBackStack()
            navController.navigate(Screens.Login.route)
        }
    })

    AReaderTheme {
        Home(viewModel = viewModel, navController = navController)
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Home(viewModel: HomeScreenViewModel, navController: NavController) {
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
                            Text(
                                text = "A.Reader",
                                color = MaterialTheme.colors.secondary,
                                fontSize = 19.sp
                            )

                        }

                        IconButton(onClick = {
                            viewModel.onEvent(HomeScreenUiEvent.SingOut)
                            navController.popBackStack()
                            navController.navigate(Screens.Login.route)
                        }) {
                            Icon(
                                modifier = Modifier
                                    .size(30.dp),
                                imageVector = Icons.Outlined.Logout,
                                contentDescription = null,
                                tint = MaterialTheme.colors.secondaryVariant
                            )
                        }
                    }
                }
            },
            backgroundColor = MaterialTheme.colors.background,
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {

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
                    text = viewModel.currentUser.value.userName,
                    style = MaterialTheme.typography.overline,
                    color = MaterialTheme.colors.error,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
            }
        }

        Spacer(modifier = Modifier.padding(top = 8.dp))

        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState(),)
        ) {
            BookCard()
            Spacer(modifier = Modifier.width(12.dp))
            BookCard()
            Spacer(modifier = Modifier.width(12.dp))
            BookCard()
            Spacer(modifier = Modifier.width(12.dp))
            BookCard()
            Spacer(modifier = Modifier.width(12.dp))
            BookCard()
            Spacer(modifier = Modifier.width(12.dp))
            BookCard()
            Spacer(modifier = Modifier.width(12.dp))
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        ShowTitle(title = "Pending List")
    
        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
        ) {
            BookCard()
            Spacer(modifier = Modifier.width(12.dp))
            BookCard()
            Spacer(modifier = Modifier.width(12.dp))
            BookCard()
            Spacer(modifier = Modifier.width(12.dp))
            BookCard()
            Spacer(modifier = Modifier.width(12.dp))
            BookCard()
            Spacer(modifier = Modifier.width(12.dp))
            BookCard()
            Spacer(modifier = Modifier.width(12.dp))
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

@Composable
fun ShowBookTitleAndAuthor(bookTitle: String, author: String) {
    Column {
        ShowBootTitle(bookTitle = bookTitle)
        ShowAuthor(author = author)
    }
}

@Composable
fun ShowBootTitle(bookTitle: String) {
    Text(
        modifier = Modifier.padding(start = 10.dp),
        text = bookTitle,
        style = MaterialTheme.typography.subtitle2.copy(
            fontWeight = FontWeight.Bold,
            fontFamily = Font(R.font.joan_regular).toFontFamily()
        )
    )
}

@Composable
fun ShowAuthor(author: String) {
    Text(
        modifier = Modifier.padding(start = 10.dp),
        text = author,
        style = MaterialTheme.typography.caption
    )
}


@Preview
@Composable
fun BookCard(
    book: MBook = MBook("f", "Why Android", "Islam", "OH hey"),
    onPressed: (String) -> Unit = {}
) {
    val context = LocalContext.current
    val resources = context.resources
    val displayMatrix = resources.displayMetrics
    val screenWidth = displayMatrix.widthPixels / displayMatrix.density


    Card(
        modifier = Modifier
            .height(242.dp)
            .width(202.dp)
            .clickable() { onPressed.invoke(book.id.toString()) },
        elevation = 6.dp,
        shape = RoundedCornerShape(29.dp),
        backgroundColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .width((screenWidth - 20).dp)
                .padding(bottom = 24.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .padding(6.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {

                ShowBookImage(imageUrl = "http://books.google.com/books/content?id=77RZAAAAYAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api")
                BookRating()

            }

            ShowBookTitleAndAuthor("Islam Bourouba", author = "islam, mouhamed")
        }
    }
}

@Composable
fun ShowBookImage(imageUrl: String) {
    AsyncImage(
        modifier = Modifier
            .height(140.dp)
            .width(100.dp),
        model = imageUrl,
        contentDescription = null
    )
}

@Composable
fun BookRating() {

    val star = remember {
        mutableStateOf(false)
    }
    val fav = remember {
        mutableStateOf(false)
    }

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(onClick = { fav.value = !fav.value }) {
            if (!fav.value)
                Icon(
                    imageVector = Icons.Rounded.FavoriteBorder,
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            else
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = null,
                    tint = Color.Unspecified
                )
        }

        Surface(
            modifier = Modifier
                .height(90.dp)
                .padding(3.dp),
            shape = RoundedCornerShape(37.dp),
            elevation = 6.dp,
            border = BorderStroke(1.dp, color = Color.Black)
        ) {
            Column(
                modifier = Modifier
                    .padding(bottom = 4.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                IconButton(onClick = { star.value = !star.value }) {
                    if (!star.value)
                        Icon(
                            imageVector = Icons.Rounded.StarBorder,
                            contentDescription = null,
                            tint = Color.Unspecified
                        )
                    else
                        Icon(
                            imageVector = Icons.Rounded.Star,
                            contentDescription = null,
                            tint = Color.Unspecified
                        )
                }
                Text(text = "0.0", style = MaterialTheme.typography.subtitle1)
            }
        }
    }

}

