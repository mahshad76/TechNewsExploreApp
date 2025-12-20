package com.mahshad.welcome.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor() : ViewModel() {
    private val _userNameStateFlow: MutableStateFlow<String> = MutableStateFlow("")
    val usernameStateFlow = _userNameStateFlow.asStateFlow()

    private val _passwordStateFlow: MutableStateFlow<String> = MutableStateFlow("")
    val passwordStateFlow = _passwordStateFlow.asStateFlow()

    private val _isEnabled: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isEnabled = _isEnabled.asStateFlow()

    fun updateFlow(update: String, type: Boolean) {
        if (type) _userNameStateFlow.value = update else
            _passwordStateFlow.value = update
    }

    fun updateIsEnabled() {
        _userNameStateFlow.combine(_passwordStateFlow) { username, password ->
            if (username.length >= 7 && password.length >= 7 && password.all { char ->
                    char in 'a'..'z' || char in 'A'..'Z' || char in '0'..'9'
                }) {
                _isEnabled.value = true
            } else {
                _isEnabled.value = false
            }
        }
    }
}