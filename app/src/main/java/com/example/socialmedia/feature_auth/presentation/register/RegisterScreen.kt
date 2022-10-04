package com.example.socialmedia.feature_auth.presentation.register


import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.example.socialmedia.util.UiEvent
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.flow.collectLatest


@Composable
fun RegisterScreen(
    onNavigate: (String) -> Unit = {},
    onNavigatePopBackStack: () -> Unit = {},
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val systemUiController: SystemUiController = rememberSystemUiController()
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        systemUiController.setStatusBarColor(
            color = Color(android.graphics.Color.parseColor("#C1CED6"))
        )
    }
    LaunchedEffect(key1 = context) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is UiEvent.Success -> {
                    Toast.makeText(
                        context,
                        event.uiText.asString(context),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
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
                    UsernameAndEmailAndPassword()
                    Spacer(modifier = Modifier.height(40.dp))
                    StandardButton(
                        text = stringResource(id = R.string.sign_in),
                        onClick = {
                            viewModel.onEvent(Events.Register)
                        },
                        enabled = !viewModel.state.isLoading
                    )
                    Spacer(modifier = Modifier.height(40.dp))
                    TextAlready(onNavigatePopBackStack = onNavigatePopBackStack)
                    if(viewModel.state.isLoading){
                        CircularProgressIndicator(
                            color = Color(android.graphics.Color.parseColor("#8CDCE1"))
                        )
                    }
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
    val state = viewModel.state
    StandardTextField(
        onValueChange = {
            viewModel.onEvent(Events.EnteredEmail(it))
        },
        text = state.emailText,
        error = state.emailError != null,
        hint = stringResource(id = R.string.email_hint),
        errorText = {
            if(state.emailError != null){
                Text(
                    text = state.emailError.asString(),
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
    )
    Spacer(modifier = modifier.height(20.dp))
    StandardTextField(
        onValueChange = {
            viewModel.onEvent(Events.EnteredUsername(it))
        },
        text = state.usernameText,
        error = state.usernameError != null,
        hint = stringResource(id = R.string.username_hint),
        errorText = {
            if(state.usernameError != null){
                Text(
                    text =  state.usernameError.asString(),
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
    )
    Spacer(modifier = modifier.height(20.dp))
    StandardTextField(
        onValueChange = {
            viewModel.onEvent(Events.EnteredPassword(it))
        },
        text = state.passwordText,
        error = state.passwordError != null,
        keyboardType = KeyboardType.Password,
        hint = stringResource(id = R.string.password_hint),
        showPasswordToggle = state.showPassword,
        onPasswordToggleClicked = {
            viewModel.onEvent(Events.ShowPassword)
        },
        errorText = {
            if(state.passwordError != null){
                Text(
                    text =  state.passwordError.asString(),
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
    )
}

@Composable
fun TextAlready(
    modifier: Modifier = Modifier,
    onNavigatePopBackStack: () -> Unit = {}
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
                onNavigatePopBackStack()
            },
        color = Color.Black,
        textAlign = TextAlign.Center
    )
}