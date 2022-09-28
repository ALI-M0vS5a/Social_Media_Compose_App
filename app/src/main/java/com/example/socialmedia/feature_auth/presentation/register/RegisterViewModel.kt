package com.example.socialmedia.feature_auth.presentation.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socialmedia.feature_auth.domain.use_case.ValidateEmail
import com.example.socialmedia.feature_auth.domain.use_case.ValidatePassword
import com.example.socialmedia.feature_auth.domain.use_case.ValidateUsername
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword,
    private val validateUsername: ValidateUsername
) : ViewModel() {

    var state by mutableStateOf(RegisterScreenState())

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

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
        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }
    }

    sealed class ValidationEvent {
        object Success: ValidationEvent()
    }
}

