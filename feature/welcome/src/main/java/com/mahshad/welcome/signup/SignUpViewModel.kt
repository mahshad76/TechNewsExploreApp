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
class SignUpViewModel @Inject constructor(private val authRepository: AuthRepository) :
    ViewModel() {
    private val _userNameStateFlow: MutableStateFlow<String> = MutableStateFlow("")
    val usernameStateFlow = _userNameStateFlow.asStateFlow()

    private val _passwordStateFlow: MutableStateFlow<String> = MutableStateFlow("")
    val passwordStateFlow = _passwordStateFlow.asStateFlow()

    private val _signUpStatusFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val signUpStatusFlow = _signUpStatusFlow.asStateFlow()

    val isEnabled: StateFlow<Boolean> =
        _userNameStateFlow.combine(_passwordStateFlow) { username, password ->
            if (username.length >= 7 && password.length >= 7 && password.all { char ->
                    char in 'a'..'z' || char in 'A'..'Z' || char in '0'..'9' ||
                            char in listOf('@', '_')
                }) {
                true
            } else {
                false
            }
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            false
        )

    fun updateFlow(update: String, type: Boolean) {
        if (type) _userNameStateFlow.value = update else
            _passwordStateFlow.value = update
    }

    fun signUp(username: String, password: String) {
        authRepository.signUp(username, password, {
            if (it) _signUpStatusFlow.value = true
            else _signUpStatusFlow.value = false
        })
    }
}