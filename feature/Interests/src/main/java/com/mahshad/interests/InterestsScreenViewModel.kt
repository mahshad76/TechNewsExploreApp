package com.mahshad.interests

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahshad.data.repository.UserDataRepository
import com.mahshad.ui.UserDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InterestsScreenViewModel @Inject constructor(private val userDataRepository: UserDataRepository) :
    ViewModel() {
    val interestingTopicStateFlow: StateFlow<UserDataState<Set<String>>> =
        userDataRepository.getUserData()
            .map { UserDataState.Success(it) }
            .catch { UserDataState.Error(it) }
            .stateIn(
                viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                UserDataState.Loading
            )

    fun update(topic: String) {
        if (interestingTopicStateFlow.value is UserDataState.Success) {
            val favoriteTopics =
                (interestingTopicStateFlow.value as UserDataState.Success).favoriteTopics
                    .toMutableSet()
            viewModelScope.launch {
                if (topic in favoriteTopics) {
                    favoriteTopics.remove(topic)
                    userDataRepository.postUserData(favoriteTopics)
                } else {
                    favoriteTopics.add(topic)
                    userDataRepository.postUserData(favoriteTopics)
                }
            }
        }
    }
}