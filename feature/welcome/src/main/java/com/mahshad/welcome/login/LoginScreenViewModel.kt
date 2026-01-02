package com.mahshad.welcome.login

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
class LoginScreenViewModel @Inject constructor(private val authRepository: AuthRepository) :
    ViewModel() {

    private val _userNameStateFlow: MutableStateFlow<String> = MutableStateFlow("")
    val usernameStateFlow = _userNameStateFlow.asStateFlow()

    private val _passwordStateFlow: MutableStateFlow<String> = MutableStateFlow("")
    val passwordStateFlow = _passwordStateFlow.asStateFlow()

    private val _loginStatusFlow: MutableStateFlow<LoginState> =
        MutableStateFlow(LoginState.Waiting)
    val loginStatusFlow = _loginStatusFlow.asStateFlow()

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

    fun login(username: String, password: String) {
        authRepository.login(username, password, {
            if (it) _loginStatusFlow.value = LoginState.Success
            else _loginStatusFlow.value = LoginState.Failure
        })
    }
}

sealed interface LoginState {
    data object Success : LoginState
    data object Failure : LoginState
    data object Waiting : LoginState
}