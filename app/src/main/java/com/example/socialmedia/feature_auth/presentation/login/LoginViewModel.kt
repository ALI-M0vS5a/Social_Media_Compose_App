package com.example.socialmedia.feature_auth.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socialmedia.R
import com.example.socialmedia.feature_auth.domain.use_case.LoginUseCase
import com.example.socialmedia.feature_auth.domain.use_case.ValidateLoginEmailAndPasswordUseCase
import com.example.socialmedia.presentation.main_feed.MainFeedScreen
import com.example.socialmedia.presentation.util.Screen
import com.example.socialmedia.util.Resource
import com.example.socialmedia.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val validateLoginEmailAndPasswordUseCase: ValidateLoginEmailAndPasswordUseCase,
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    var state by mutableStateOf(LoginScreenState())

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(events: LoginEvents) {
        when (events) {
            is LoginEvents.EnteredEmail -> {
                state = state.copy(
                    emailText = events.email
                )
            }
            is LoginEvents.EnteredPassword -> {
                state = state.copy(
                    passwordText = events.password
                )
            }
            is LoginEvents.ShowPassword -> {
                state = state.copy(
                    showPassword = !state.showPassword
                )
            }
            is LoginEvents.Login -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val emailAndPasswordResult =
            validateLoginEmailAndPasswordUseCase.execute(state.emailText, state.passwordText)
        val hasError = emailAndPasswordResult.let { !it.successful }

        if (hasError) {
            state = state.copy(
                emailError = emailAndPasswordResult.errorMessage,
                passwordError = emailAndPasswordResult.errorMessage
            )
            return
        } else {
            state = state.copy(
                emailError = null,
                passwordError = null
            )
        }
        login()
    }

    private fun login() {
        if (state.emailError != null || state.passwordError != null) {
            return
        }
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val result = loginUseCase(
                email = state.emailText,
                password = state.passwordText
            )
            state = when (result) {
                is Resource.Success -> {
                    _eventFlow.emit(
                        UiEvent.Navigate(Screen.MainFeedScreen.route)
                    )
                    state.copy(isLoading = false)
                }
                is Resource.Error -> {
                    _eventFlow.emit(
                        UiEvent.Message(result.uiText ?: UiText.unknownError())
                    )
                    state.copy(isLoading = false)
                }
            }
        }
    }

    sealed class UiEvent {
        data class Message(val uiText: UiText): UiEvent()
        data class Navigate(val route: String): UiEvent()
    }
}