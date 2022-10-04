package com.example.socialmedia.feature_post.presentation.create_post

import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socialmedia.R
import com.example.socialmedia.feature_post.domian.use_case.PostUseCases
import com.example.socialmedia.presentation.util.states.StandardTextFieldState
import com.example.socialmedia.util.Resource
import com.example.socialmedia.util.UiEvent
import com.example.socialmedia.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreatePostViewModel @Inject constructor(
    private val postUseCases: PostUseCases
) : ViewModel() {

    private val _description = mutableStateOf(StandardTextFieldState())
    val description: State<StandardTextFieldState> = _description

    private val _chosenImageUri = mutableStateOf<Uri?>(null)
    val chosenImageUri: State<Uri?> = _chosenImageUri

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()


    fun onEvent(events: CreatePostEvents) {
        when(events) {
            is CreatePostEvents.EnterDescription -> {
                _description.value = description.value.copy(
                    text = events.value
                )
            }
            is CreatePostEvents.PickImage -> {
                _chosenImageUri.value = events.uri
            }
            is CreatePostEvents.CropImage -> {
                _chosenImageUri.value = events.uri
            }
            is CreatePostEvents.PostImage -> {
                    viewModelScope.launch {
                        _isLoading.value = true
                        val result = postUseCases.createPostUseCase(
                            description = description.value.text,
                            imageUri = chosenImageUri.value
                        )
                        when(result) {
                            is Resource.Success -> {
                                _eventFlow.emit(UiEvent.Message(UiText.StringResource(R.string.success_post)))
                                _eventFlow.emit(UiEvent.NavigateUp)
                            }

                            is Resource.Error -> {
                                _eventFlow.emit(UiEvent.Message(result.uiText ?: UiText.unknownError()))
                            }
                        }
                        _isLoading.value = false
                    }
                }
            }
        }
    }