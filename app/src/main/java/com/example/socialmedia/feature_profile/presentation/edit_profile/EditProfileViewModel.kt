package com.example.socialmedia.feature_profile.presentation.edit_profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(

) : ViewModel() {

    private val _uiState = mutableStateOf(EditProfileScreenState())
    val uiState: State<EditProfileScreenState> = _uiState

    fun setUsername(username: String){
        _uiState.value = uiState.value.copy(
            username = username
        )
    }
    fun setGithubUrlText(githubUrlText: String){
        _uiState.value = uiState.value.copy(
            github = githubUrlText
        )
    }
    fun setInstagramUrlText(instagramUrlText: String){
        _uiState.value = uiState.value.copy(
            instagram = instagramUrlText
        )
    }
    fun setLinkedinUrlText(linkedinUrlText: String){
        _uiState.value = uiState.value.copy(
            linkedin = linkedinUrlText
        )
    }
    fun setYourBio(yourBio: String){
        _uiState.value = uiState.value.copy(
            yourBio = yourBio
        )
    }

}