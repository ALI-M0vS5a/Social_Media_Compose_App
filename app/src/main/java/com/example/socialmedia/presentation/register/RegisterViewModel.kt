package com.example.socialmedia.presentation.register

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(

) : ViewModel() {

    private val _uiState = mutableStateOf(RegisterScreenState())
    val uiState: State<RegisterScreenState> = _uiState

    fun setEmailText(email: String){
        _uiState.value = uiState.value.copy(
            emailText = email
        )
    }

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

    fun setShowPassword(showPassword: Boolean) {
        _uiState.value = uiState.value.copy(
            showPassword = showPassword
        )
    }

    fun setIsEmailError(error: String){
        _uiState.value = uiState.value.copy(
            emailError = error
        )
    }

    fun setIsUsernameError(error: String){
        _uiState.value = uiState.value.copy(
            usernameError = error
        )
    }
    fun setIsPasswordError(error:String){
        _uiState.value = uiState.value.copy(
            passwordError = error
        )
    }

}