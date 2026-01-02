package com.mahshad.welcome.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahshad.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SignUpScreenViewModel @Inject constructor(private val authRepository: AuthRepository) :
    ViewModel() {
    private val _userNameStateFlow: MutableStateFlow<String> = MutableStateFlow("")
    val usernameStateFlow = _userNameStateFlow.asStateFlow()

    private val _passwordStateFlow: MutableStateFlow<String> = MutableStateFlow("")
    val passwordStateFlow = _passwordStateFlow.asStateFlow()

    private val _passwordConfirmationStateFlow: MutableStateFlow<String> = MutableStateFlow("")
    val passwordConfirmationStateFlow = _passwordConfirmationStateFlow.asStateFlow()

    private val _signUpStatusFlow: MutableStateFlow<SignUpStatus> =
        MutableStateFlow(SignUpStatus.Idle)
    val signUpStatusFlow = _signUpStatusFlow.asStateFlow()

    val isEnabled: StateFlow<Boolean> =
        combine(
            _userNameStateFlow,
            _passwordStateFlow,
            _passwordConfirmationStateFlow
        ) { username, password, confirmation ->
            if (username.length >= 7 && password.length >= 7 && password.all { char ->
                    char in 'a'..'z' || char in 'A'..'Z' || char in '0'..'9' ||
                            char in listOf('@', '_')
                } &&
                password == confirmation) true else false
        }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                false
            )

    fun updateFlow(update: String, type: Int) {
        when (type) {
            1 -> _userNameStateFlow.value = update
            2 -> _passwordStateFlow.value = update
            3 -> _passwordConfirmationStateFlow.value = update
        }
    }

    fun signUp(username: String, password: String) {
        authRepository.signUp(username, password, {
            if (it) _signUpStatusFlow.value = SignUpStatus.Success
            else _signUpStatusFlow.value = SignUpStatus.Failure
        })
    }
}

sealed interface SignUpStatus {
    data object Success : SignUpStatus
    data object Failure : SignUpStatus
    data object Idle : SignUpStatus
}