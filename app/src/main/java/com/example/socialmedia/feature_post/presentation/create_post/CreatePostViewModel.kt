package com.example.socialmedia.feature_post.presentation.create_post

import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socialmedia.feature_post.domian.use_case.PostUseCases
import com.example.socialmedia.presentation.util.states.StandardTextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
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
                chosenImageUri.value?.let { uri ->
                    viewModelScope.launch {
                        postUseCases.createPostUseCase(
                            description = description.value.text,
                            imageUri = uri
                        )
                    }
                }
            }
        }
    }
}