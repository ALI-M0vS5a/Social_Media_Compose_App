package com.example.socialmedia.presentation.register


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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


@Composable
fun RegisterScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(
                    color = Color(android.graphics.Color.parseColor("#C1CED6"))
                )
        ) {
            Text(
                text = stringResource(id = R.string.welcome),
                color = androidx.compose.ui.graphics.Color.White,
                fontWeight = FontWeight(700),
                fontSize = 40.sp,
                lineHeight = 48.72.sp,
                letterSpacing = 10.sp,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 175.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(600.dp)
                    .background(
                        color = androidx.compose.ui.graphics.Color.White,
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
                    UsernameAndEmailAndPassword()
                    Spacer(modifier = Modifier.height(40.dp))
                    StandardButton(
                        text = stringResource(id = R.string.sign_in)
                    )
                    Spacer(modifier = Modifier.height(40.dp))
                    TextAlready(navController = navController)
                }
            }
        }
    }
}

@Composable
fun UsernameAndEmailAndPassword(
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    StandardTextField(
        onValueChange = {
            viewModel.setEmailText(it)
        },
        text = viewModel.uiState.value.emailText,
        error = viewModel.uiState.value.emailError,
        hint = stringResource(id = R.string.email_hint)
    )
    Spacer(modifier = modifier.height(20.dp))
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
fun TextAlready(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Text(
        text = buildAnnotatedString {
            append(stringResource(id = R.string.already_have_an_account))
            append(" ")
            val signupText = stringResource(id = R.string.sign_in)
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
               navController.popBackStack()
            },
        color = Color.Black,
        textAlign = TextAlign.Center
    )
}