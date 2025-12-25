package com.mahshad.interests

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahshad.data.repository.UserDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InterestsScreenViewModel @Inject constructor(private val userDataRepository: UserDataRepository) :
    ViewModel() {

    val checkedStateFlow: StateFlow<Set<String>> = userDataRepository.getUserData()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            emptySet()
        )

    fun updateCheckedState(favoriteTopics: Set<String>) {
        viewModelScope.launch {
            userDataRepository.postUserData(favoriteTopics)
        }
    }
}

sealed interface UiState {
    data class Success(val favoriteTopics: Set<String>) : UiState
    data class Error(val error: Throwable) : UiState
    data object Loading : UiState
}