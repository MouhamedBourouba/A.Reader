package com.example.areader.prestion.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.ScrollAxisRange
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.areader.model.UserInfo
import com.example.areader.prestion.components.ShowLoading
import com.example.areader.prestion.components.ShowLogo
import com.example.areader.prestion.components.StandardButton
import com.example.areader.prestion.components.StandardTextFiled
import com.example.areader.prestion.theme.AReaderTheme
import com.example.areader.prestion.viewmodels.LoginViewModel
import com.example.areader.utils.Screens
import es.dmoral.toasty.Toasty

@Composable
fun LoginScreen(viewModel: LoginViewModel = hiltViewModel(), navController: NavController) {

    val isLogin = rememberSaveable {
        mutableStateOf(true)
    }

    val isLoading = rememberSaveable {
        mutableStateOf(false)
    }



    if (isLogin.value) ShowLogin(
        viewModel,
        onBtnClick = { isLoading.value = true },
        onActionEnd = {
            isLoading.value = false
            if (it) {
                navController.popBackStack()
                navController.navigate(Screens.Home.route)
            }
        },
        changeScreen = { isLogin.value = false }
    )
    else ShowRegister(
        viewModel,
        onBtnClick = { isLoading.value = true },
        onActionEnd = {
            isLoading.value = false
            if (it) {
                navController.navigate(Screens.Home.route)
            }
        },
        changeScreen = { isLogin.value = true }
    )

    if (isLoading.value) ShowLoading()

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ShowLogin(
    viewModel: LoginViewModel,
    changeScreen: () -> Unit,
    onBtnClick: () -> Unit,
    onActionEnd: (isSucssesfuly: Boolean) -> Unit
) {
    val context = LocalContext.current

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

                val emailText = rememberSaveable {
                    mutableStateOf("")
                }
                val passwordText = rememberSaveable {
                    mutableStateOf("")
                }

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
                        value = emailText.value,
                        onChangeListener = {
                            if (it.length < 30)
                                emailText.value = it
                        },
                        placeholder = "Email",
                        leadingIcon = Icons.Outlined.Email,
                        keyboardType = KeyboardType.Email
                    )

                    Spacer(modifier = Modifier.padding(top = 8.dp))


                    StandardTextFiled(
                        value = passwordText.value,
                        onChangeListener = {
                            if (it.length < 30)
                                passwordText.value = it
                        },
                        placeholder = "Password",
                        leadingIcon = Icons.Outlined.Lock,
                        isPasswordTextFieldValue = true
                    )


                    Spacer(modifier = Modifier.padding(top = 8.dp))


                    Box(modifier = Modifier.align(Alignment.End)) {
                        StandardButton(buttonText = "singIn") {

                            onBtnClick()

                            if (
                                emailText.value.isNotBlank() &&
                                passwordText.value.isNotBlank()
                            ) viewModel.loginWithEmailAndPassword(
                                emailText.value,
                                passwordText.value,
                                onSuccess = {
                                    Toasty.success(context, "GG", Toasty.LENGTH_SHORT).show()
                                    onActionEnd(true)
                                },
                                onFailed = {
                                    Toasty.error(context, it.toString(), Toasty.LENGTH_SHORT).show()
                                    onActionEnd(false)
                                })
                            else {
                                Toasty.error(
                                    context,
                                    "Please enter your Information",
                                    Toasty.LENGTH_SHORT
                                ).show()
                                onActionEnd(false)
                            }
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
                            )
                        ) {
                            append("Create Account!")
                        }
                    })
            }
        }
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ShowRegister(
    viewModel: LoginViewModel,
    changeScreen: () -> Unit,
    onBtnClick: () -> Unit,
    onActionEnd: (isSucssesfuly: Boolean) -> Unit
) {
    val context = LocalContext.current

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

                val userNameText = rememberSaveable {
                    mutableStateOf("")
                }

                val emailText = rememberSaveable {
                    mutableStateOf("")
                }
                val passwordText = rememberSaveable {
                    mutableStateOf("")
                }

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
                        value = userNameText.value,
                        onChangeListener = {
                            if (it.length < 30)
                                userNameText.value = it
                        },
                        placeholder = "Name",
                        leadingIcon = Icons.Outlined.Person,
                        keyboardType = KeyboardType.Text
                    )


                    Spacer(modifier = Modifier.padding(top = 8.dp))


                    StandardTextFiled(
                        value = emailText.value,
                        onChangeListener = {
                            if (it.length < 30)
                                emailText.value = it
                        },
                        placeholder = "Email",
                        leadingIcon = Icons.Outlined.Email,
                        keyboardType = KeyboardType.Email
                    )


                    Spacer(modifier = Modifier.padding(top = 8.dp))


                    StandardTextFiled(
                        value = passwordText.value,
                        onChangeListener = {
                            if (it.length < 30)
                                passwordText.value = it
                        },
                        placeholder = "Password",
                        leadingIcon = Icons.Outlined.Lock,
                        isPasswordTextFieldValue = true
                    )


                    Spacer(modifier = Modifier.padding(top = 8.dp))



                    Box(modifier = Modifier.align(Alignment.End)) {
                        StandardButton(buttonText = "singUp") {
                            onBtnClick()

                            if (
                                emailText.value.isNotBlank() &&
                                passwordText.value.isNotBlank() &&
                                userNameText.value.isNotBlank()
                            ) viewModel.createUser(
                                UserInfo(emailText.value, userNameText.value),
                                passwordText.value,
                                onSuccess = {
                                    Toasty.success(context, "GG", Toast.LENGTH_SHORT).show()
                                    onActionEnd(true)
                                },
                                onFailed = {
                                    Toasty.error(context, "$it", Toast.LENGTH_SHORT).show()
                                    onActionEnd(false)
                                }
                            )
                            else {
                                Toasty.error(
                                    context,
                                    "Please enter your Information",
                                    Toasty.LENGTH_SHORT
                                ).show()
                                onActionEnd(false)
                            }
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

