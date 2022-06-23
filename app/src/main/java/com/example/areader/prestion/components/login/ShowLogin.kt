package com.example.areader.prestion.components.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.areader.data.AuthResult
import com.example.areader.prestion.components.ShowLogo
import com.example.areader.prestion.components.StandardButton
import com.example.areader.prestion.components.StandardTextFiled
import com.example.areader.prestion.screens.destinations.HomeScreenDestination
import com.example.areader.prestion.screens.loginScreen.AuthScreenUiEvent
import com.example.areader.prestion.screens.loginScreen.AuthViewModel
import com.example.areader.prestion.theme.AReaderTheme
import com.example.areader.utils.Screens
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import es.dmoral.toasty.Toasty


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ShowLogin(
    viewModel: AuthViewModel,
    navController: DestinationsNavigator,
    changeScreen: () -> Unit,
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = viewModel) {
        viewModel.singInResult.collect {
            when (it) {
                is AuthResult.Authorized -> {
                    navController.popBackStack()
                    navController.navigate(HomeScreenDestination)
                }
                is AuthResult.UnAuthorized -> Toasty.error(context, it.message.toString()).show()
                is AuthResult.UnknownError -> Toasty.error(
                    context,
                    "Looks like you don't have internet connection !!",
                    Toasty.LENGTH_LONG
                ).show()
            }
        }
    }



    AReaderTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ShowLogo()

                Column {

                    Text(
                        modifier = Modifier
                            .align(alignment = Alignment.Start)
                            .padding(
                                start = 15.dp,
                                bottom = 8.dp
                            ),
                        text = "Login :",
                        style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold)
                    )

                    StandardTextFiled(
                        value = viewModel.loginState.loginUserNameOrEmailText,
                        onChangeListener = {
                            if (it.length < 30)
                                viewModel.onEvent(
                                    AuthScreenUiEvent.LoginUsernameOrEmailTextChanged(
                                        it
                                    )
                                )
                        },
                        placeholder = "Email/Username",
                        leadingIcon = Icons.Outlined.Person,
                        keyboardType = KeyboardType.Email
                    )

                    Spacer(modifier = Modifier.padding(top = 8.dp))


                    StandardTextFiled(
                        value = viewModel.loginState.loginPasswordText,
                        onChangeListener = {
                            if (it.length < 30)
                                viewModel.onEvent(AuthScreenUiEvent.LoginPasswordTextChanged(it))
                        },
                        placeholder = "Password",
                        leadingIcon = Icons.Outlined.Lock,
                        isPasswordTextFieldValue = true
                    )


                    Spacer(modifier = Modifier.padding(top = 8.dp))


                    Box(modifier = Modifier.align(Alignment.End)) {
                        StandardButton(
                            buttonText = "singIn",
                            isEnabled = viewModel.loginState.isSingInButtonEnabled
                        ) {
                            viewModel.onEvent(authEvent = AuthScreenUiEvent.SingIn)
                        }
                    }
                }



                Text(
                    modifier = Modifier.clickable {
                        changeScreen()
                    },
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Color.Black
                            )
                        ) {
                            append("Don't Have Account? ")
                        }
                        withStyle(
                            style = SpanStyle(
                                color = Color.Blue.copy(alpha = 0.7f)
                            ),
                        ) {
                            append("Create Account!")
                        }
                    }
                )
            }
        }
    }
}

