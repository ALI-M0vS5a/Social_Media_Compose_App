package com.example.socialmedia.feature_post.presentation.create_post

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.socialmedia.presentation.util.states.StandardTextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreatePostViewModel @Inject constructor(

) : ViewModel() {

    private val _description = mutableStateOf(StandardTextFieldState())
    val description: State<StandardTextFieldState> = _description

    fun setDescriptionText(state: StandardTextFieldState){
        _description.value = state
    }
}