package com.example.socialmedia.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(

) : ViewModel() {

    private val _uiState = mutableStateOf(LoginScreenState())
    val uiState: State<LoginScreenState> = _uiState

    fun setUsernameText(username: String) {
        _uiState.value = uiState.value.copy(
            usernameText = username
        )
    }

    fun setPasswordText(password: String) {
        _uiState.value = uiState.value.copy(
            passwordText = password
        )
    }
}