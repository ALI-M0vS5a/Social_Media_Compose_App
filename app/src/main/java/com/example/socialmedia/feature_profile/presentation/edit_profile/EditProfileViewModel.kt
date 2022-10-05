package com.example.socialmedia.feature_profile.presentation.edit_profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socialmedia.R
import com.example.socialmedia.feature_profile.domain.model.UpdateProfileData
import com.example.socialmedia.feature_profile.domain.use_case.ProfileUseCases
import com.example.socialmedia.util.Resource
import com.example.socialmedia.util.UiEvent
import com.example.socialmedia.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val profileUseCases: ProfileUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = mutableStateOf(EditProfileScreenState())
    val uiState: State<EditProfileScreenState> = _uiState

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        savedStateHandle.get<String>("userId")?.let { userId ->
            getSkills()
            getProfile(userId)
        }
    }

    private fun getSkills() {
        viewModelScope.launch {
            when(val result = profileUseCases.getSkillsUseCase()) {
                is Resource.Success -> {
                    _uiState.value = uiState.value.copy(
                        skills = result.data ?: kotlin.run {
                            _eventFlow.emit(UiEvent.Message(UiText.StringResource(R.string.error_loading_skills)))
                            return@launch
                        }
                    )
                }
                is Resource.Error -> {
                    _eventFlow.emit(UiEvent.Message(result.uiText ?: UiText.unknownError()))
                    return@launch
                }
            }
        }
    }
    private fun getProfile(userId: String) {
        viewModelScope.launch {
            _uiState.value = uiState.value.copy(isLoading = true)
            when (val result = profileUseCases.getProfileUseCase(userId)) {
                is Resource.Success -> {
                    val profile = result.data ?: kotlin.run {
                        _eventFlow.emit(UiEvent.Message(UiText.StringResource(R.string.error_couldnt_load_profile)))
                        return@launch
                    }
                    _uiState.value = uiState.value.copy(
                        username = profile.username
                    )
                    _uiState.value = uiState.value.copy(
                        github = profile.gitHubUrl ?: ""
                    )
                    _uiState.value = uiState.value.copy(
                        instagram = profile.instagramUrl ?: ""
                    )
                    _uiState.value = uiState.value.copy(
                        linkedin = profile.linkedinUrl ?: ""
                    )
                    _uiState.value = uiState.value.copy(
                        yourBio = profile.bio
                    )
                    _uiState.value = uiState.value.copy(
                        selectedSkills = profile.topSkills
                    )
                    _uiState.value = uiState.value.copy(
                        profile = profile,
                        isLoading = false
                    )
                }
                is Resource.Error -> {
                    _eventFlow.emit(UiEvent.Message(result.uiText ?: UiText.unknownError()))
                    return@launch
                }
            }
        }
    }
    private fun updateProfile() {
        viewModelScope.launch {
            val result = profileUseCases.updateProfileUseCase(
                updateProfileData = UpdateProfileData(
                    username = uiState.value.username,
                    bio = uiState.value.yourBio,
                    gitHubUrl = uiState.value.github,
                    instagramUrl = uiState.value.instagram,
                    linkedinUrl = uiState.value.linkedin,
                    skills = uiState.value.selectedSkills
                ),
                profilePictureUri = uiState.value.profilePictureUri,
                bannerPictureUri = uiState.value.bannerUri
            )
            when(result) {
                is Resource.Success -> {
                    _eventFlow.emit(
                        UiEvent.Message(UiText.StringResource(R.string.updated_profile))
                    )
                    return@launch
                }
                is Resource.Error -> {
                    _eventFlow.emit(
                        UiEvent.Message(result.uiText ?: UiText.unknownError())
                    )
                    return@launch
                }
            }
        }
    }
    fun onEvent(event: EditProfileEvent) {
        when (event) {
            is EditProfileEvent.EnteredUsername -> {
                _uiState.value = uiState.value.copy(
                    username = event.username
                )
            }
            is EditProfileEvent.EnteredGitHubUrl -> {
                _uiState.value = uiState.value.copy(
                    github = event.gitHubUrl
                )
            }
            is EditProfileEvent.EnteredInstagramUrl -> {
                _uiState.value = uiState.value.copy(
                    instagram = event.instagram
                )
            }
            is EditProfileEvent.EnteredLinkedinUrl -> {
                _uiState.value = uiState.value.copy(
                    linkedin = event.linkedin
                )
            }
            is EditProfileEvent.EnteredBio -> {
                _uiState.value = uiState.value.copy(
                    yourBio = event.bio
                )
            }
            is EditProfileEvent.CropProfilePicture -> {
                _uiState.value = uiState.value.copy(
                    profilePictureUri = event.pictureUri
                )
            }
            is EditProfileEvent.CropBannerImage -> {
                _uiState.value = uiState.value.copy(
                    bannerUri = event.bannerUri
                )
            }
            is EditProfileEvent.SetSkillSelected -> {

            }
            is EditProfileEvent.UpdateProfile -> {
                updateProfile()
            }
        }
    }
}