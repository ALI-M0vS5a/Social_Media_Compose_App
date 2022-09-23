package com.example.socialmedia.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Facebook
import androidx.compose.material.icons.filled.Mail
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.socialmedia.R
import com.example.socialmedia.presentation.components.StandardButton
import com.example.socialmedia.presentation.components.StandardTextField
import com.example.socialmedia.presentation.util.Screen
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun LoginScreen(
    navController: NavController
) {
    val systemUiController: SystemUiController = rememberSystemUiController()
    LaunchedEffect(key1 = true){
        systemUiController.setStatusBarColor(
            color = Color(android.graphics.Color.parseColor("#C1CED6"))
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = Color(android.graphics.Color.parseColor("#C1CED6"))
                )
        ) {
            Text(
                text = stringResource(id = R.string.welcome),
                color = Color.White,
                fontWeight = FontWeight(700),
                fontSize = 40.sp,
                lineHeight = 48.72.sp,
                letterSpacing = 10.sp,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 100.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(600.dp)
                    .background(
                        color = Color.White,
                        shape = CircleShape.copy(
                            topStart = CornerSize(35.dp),
                            topEnd = CornerSize(35.dp),
                            bottomStart = CornerSize(0.dp),
                            bottomEnd = CornerSize(0.dp)
                        )
                    )
                    .align(Alignment.BottomCenter)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(40.dp))
                    EmailAndPassword()
                    Spacer(modifier = Modifier.height(40.dp))
                    Text(
                        text = stringResource(id = R.string.forgot_password),
                        fontWeight = FontWeight(400),
                        fontSize = 14.sp,
                        lineHeight = 16.8.sp,
                        letterSpacing = 2.sp,
                        color = Color(android.graphics.Color.parseColor("#8CDCE1")),
                        modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight()
                    )
                    Spacer(modifier = Modifier.height(40.dp))
                    StandardButton(
                        text = stringResource(id = R.string.login),
                        onClick = {
                            navController.navigate(Screen.MainFeedScreen.route)
                        }
                    )
                    Spacer(modifier = Modifier.height(40.dp))
                    Text(
                        text = stringResource(id = R.string.login_by),
                        fontWeight = FontWeight(400),
                        lineHeight = 16.8.sp,
                        letterSpacing = 2.sp,
                        fontSize = 14.sp,
                        color = Color.LightGray,
                        modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight()
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                    LoginByOthers()
                    Spacer(modifier = Modifier.height(40.dp))
                    TextDonutHave(navController = navController)
                }
            }
        }
    }
}

@Composable
fun EmailAndPassword(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel()

) {
    StandardTextField(
        onValueChange = {
            viewModel.setUsernameText(it)
        },
        text = viewModel.uiState.value.usernameText,
        error = viewModel.uiState.value.usernameError,
        hint = stringResource(id = R.string.username_hint)
    )
    Spacer(modifier = modifier.height(20.dp))
    StandardTextField(
        onValueChange = {
            viewModel.setPasswordText(it)
        },
        text = viewModel.uiState.value.passwordText,
        keyboardType = KeyboardType.Password,
        hint = stringResource(id = R.string.password_hint),
        showPasswordToggle = viewModel.uiState.value.showPassword,
        onPasswordToggleClicked = {
            viewModel.setShowPassword(it)
        }
    )
}

@Composable
fun TextDonutHave(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Text(
        text = buildAnnotatedString {
            append(stringResource(id = R.string.dont_have_an_account))
            append(" ")
            val signupText = stringResource(id = R.string.sign_up)
            withStyle(
                style = SpanStyle(
                    color = Color(android.graphics.Color.parseColor("#8CDCE1"))
                )
            ) {
                append(signupText)
            }
        },
        fontWeight = FontWeight(400),
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = (-0.2).sp,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 83.5.dp)
            .height(24.dp)
            .clickable {
                navController.navigate(
                    Screen.RegisterScreen.route
                )
            },
        color = Color.Black,
        textAlign = TextAlign.Center
    )
}

@Composable
fun LoginByOthers(
    modifier: Modifier = Modifier
) {
    Row {
        OutlinedButton(
            modifier = modifier,
            shape = CircleShape,
            onClick = {

            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(android.graphics.Color.parseColor("#F3F5F7")),
                contentColor = Color(android.graphics.Color.parseColor("#BDBDBD"))
            )

        ) {
            Icon(
                imageVector = Icons.Default.Mail,
                contentDescription = stringResource(id = R.string.login_google)
            )
        }
        Spacer(modifier = Modifier.width(20.dp))
        OutlinedButton(
            modifier = modifier,
            shape = CircleShape,
            onClick = {

            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(android.graphics.Color.parseColor("#F3F5F7")),
                contentColor = Color(android.graphics.Color.parseColor("#BDBDBD"))
            )

        ) {
            Icon(
                imageVector = Icons.Default.Facebook,
                contentDescription = stringResource(id = R.string.login_facebook)
            )
        }
    }
}