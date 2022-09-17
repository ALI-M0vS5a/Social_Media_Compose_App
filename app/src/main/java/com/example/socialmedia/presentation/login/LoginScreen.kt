package com.example.socialmedia.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.socialmedia.R
import com.example.socialmedia.presentation.components.StandardTextField

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel()
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
                    color = Color.LightGray
                )
        ){
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
                EmailAndPassword()
                TextAlready(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 56.dp)
                )
            }
        }

    }
}

@Composable
fun EmailAndPassword(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel()

) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = modifier.height(40.dp))
        StandardTextField(
            onValueChange = {
                viewModel.setUsernameText(it)
            },
            text = viewModel.uiState.value.usernameText,
            hint = stringResource(id = R.string.username_hint)
        )
        Spacer(modifier = modifier.height(20.dp))
        StandardTextField(
            onValueChange = {
                viewModel.setPasswordText(it)
            },
            text = viewModel.uiState.value.passwordText,
            keyboardType = KeyboardType.Password,
            hint = stringResource(id = R.string.password_hint)
        )
    }
}

@Composable
fun TextAlready(
    modifier: Modifier = Modifier
) {
    Text(
        text = buildAnnotatedString {
            append(stringResource(id = R.string.dont_have_an_account))
            append(" ")
            val signupText = stringResource(id = R.string.sign_up)
            withStyle(
                style = SpanStyle(
                    color = Color(android.graphics.Color.parseColor("#888BF4"))
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
            .wrapContentWidth()
            .wrapContentHeight(),
        color = Color.Black
    )
}