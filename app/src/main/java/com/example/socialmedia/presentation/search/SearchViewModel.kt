package com.example.socialmedia.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(

) : ViewModel() {

    private val _uiState = mutableStateOf(SearchScreenState())
    val uiState: State<SearchScreenState> = _uiState

    fun setSearchText(search: String){
        _uiState.value = uiState.value.copy(
            search = search
        )
    }
}