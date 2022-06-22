package com.example.areader.prestion.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
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
import com.example.areader.prestion.screens.loginScreen.AuthScreenUiEvent
import com.example.areader.prestion.screens.loginScreen.AuthViewModel
import com.example.areader.prestion.theme.AReaderTheme
import com.example.areader.utils.Screens
import es.dmoral.toasty.Toasty


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ShowRegister(
    viewModel: AuthViewModel,
    navController: NavController,
    changeScreen: () -> Unit,

    ) {
    val context = LocalContext.current
    LaunchedEffect(key1 = viewModel, key2 = context) {
        viewModel.singUpResult.collect {
            when (it) {
                is AuthResult.Authorized -> {
                    navController.popBackStack()
                    navController.navigate(Screens.Home.route)
                }
                is AuthResult.UnAuthorized -> {
                    Toasty.error(context, it.message.toString()).show()
                }
                is AuthResult.UnknownError -> {
                    Toasty.error(context, it.message.toString()).show()
                }
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
                horizontalAlignment = Alignment.CenterHorizontally,
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
                        text = "Register :",
                        style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold)
                    )

                    StandardTextFiled(
                        value = viewModel.loginState.registerUserNameText,
                        onChangeListener = {
                            if (it.length < 30)
                                viewModel.onEvent(AuthScreenUiEvent.RegisterUsernameTextChanged(it))
                        },
                        placeholder = "Name",
                        leadingIcon = Icons.Outlined.Person,
                        keyboardType = KeyboardType.Text
                    )


                    Spacer(modifier = Modifier.padding(top = 8.dp))


                    StandardTextFiled(
                        value = viewModel.loginState.registerEmailText,
                        onChangeListener = {
                            if (it.length < 30)
                                viewModel.onEvent(AuthScreenUiEvent.RegisterEmailTextChanged(it))
                        },
                        placeholder = "Email",
                        leadingIcon = Icons.Outlined.Email,
                        keyboardType = KeyboardType.Email
                    )


                    Spacer(modifier = Modifier.padding(top = 8.dp))


                    StandardTextFiled(
                        value = viewModel.loginState.registerPasswordText,
                        onChangeListener = {
                            if (it.length < 30)
                                viewModel.onEvent(AuthScreenUiEvent.RegisterPasswordTextChanged(it))
                        },
                        placeholder = "Password",
                        leadingIcon = Icons.Outlined.Lock,
                        isPasswordTextFieldValue = true
                    )


                    Spacer(modifier = Modifier.padding(top = 8.dp))


                    Box(modifier = Modifier.align(Alignment.End)) {
                        StandardButton(
                            buttonText = "singUp",
                            isEnabled = viewModel.loginState.isSingUpButtonEnabled
                        ) {

                            viewModel.onEvent(AuthScreenUiEvent.SingUp)

                        }
                    }
                }

                Text(
                    modifier = Modifier
                        .clickable {
                            changeScreen()
                        },
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Color.Black
                            )
                        ) {
                            append("Already Have Account? ")
                        }
                        withStyle(
                            style = SpanStyle(
                                color = Color.Blue.copy(alpha = 0.7f)
                            )
                        ) {
                            append("Login now!")
                        }
                    })
            }
        }
    }
}
