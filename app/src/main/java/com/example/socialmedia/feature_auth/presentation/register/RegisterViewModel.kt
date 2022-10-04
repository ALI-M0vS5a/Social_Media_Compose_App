package com.example.socialmedia.feature_auth.presentation.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socialmedia.R
import com.example.socialmedia.feature_auth.domain.use_case.RegisterUseCase
import com.example.socialmedia.feature_auth.domain.use_case.ValidateEmail
import com.example.socialmedia.feature_auth.domain.use_case.ValidatePassword
import com.example.socialmedia.feature_auth.domain.use_case.ValidateUsername
import com.example.socialmedia.util.Resource
import com.example.socialmedia.util.UiEvent
import com.example.socialmedia.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword,
    private val validateUsername: ValidateUsername,
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    var state by mutableStateOf(RegisterScreenState())

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(events: Events) {
        when (events) {
            is Events.EnteredEmail -> {
                state = state.copy(
                    emailText = events.email
                )
            }
            is Events.EnteredUsername -> {
                state = state.copy(
                    usernameText = events.username
                )
            }
            is Events.EnteredPassword -> {
                state = state.copy(
                    passwordText = events.password
                )
            }
            is Events.ShowPassword -> {
                state = state.copy(
                    showPassword = !state.showPassword
                )
            }
            is Events.Register -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val emailResult = validateEmail.execute(state.emailText)
        val usernameResult = validateUsername.execute(state.usernameText)
        val passwordResult = validatePassword.execute(state.passwordText)

        val hasError = listOf(
            emailResult,
            usernameResult,
            passwordResult
        ).any { !it.successful }

        if(hasError) {
            state = state.copy(
                emailError = emailResult.errorMessage,
                usernameError = usernameResult.errorMessage,
                passwordError = passwordResult.errorMessage
            )
            return
        } else {
            state = state.copy(
                emailError = null,
                usernameError = null,
                passwordError = null
            )
        }
        register()
    }
    private fun register() {
        if(state.emailError != null || state.usernameError != null || state.passwordError != null){
            return
        }
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val result = registerUseCase(
                email = state.emailText,
                username = state.usernameText,
                password = state.passwordText
            )
            state = when(result) {
                is Resource.Success -> {
                    _eventFlow.emit(
                        UiEvent.Success(UiText.StringResource(R.string.success_registration)),
                    )
                    state.copy(isLoading = false)
                }
                is Resource.Error -> {
                    _eventFlow.emit(
                        UiEvent.Success(result.uiText ?: UiText.unknownError()),
                    )
                    state.copy(isLoading = false)
                }
            }
        }
    }
}

