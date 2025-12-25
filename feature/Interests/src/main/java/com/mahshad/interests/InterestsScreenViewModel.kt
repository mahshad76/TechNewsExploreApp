package com.mahshad.interests

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahshad.data.repository.UserDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class InterestsScreenViewModel @Inject constructor(private val userDataRepository: UserDataRepository) :
    ViewModel() {
    val interestingTopicStateFlow: StateFlow<UiState<Set<String>>> =
        userDataRepository.getUserData()
            .map { UiState.Success(it) }
            .catch { UiState.Error(it) }
            .stateIn(
                viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                UiState.Loading
            )
}

sealed interface UiState<out T> {
    data class Success(val favoriteTopics: Set<String>) : UiState<Set<String>>
    data class Error(val error: Throwable) : UiState<Nothing>
    data object Loading : UiState<Nothing>
}