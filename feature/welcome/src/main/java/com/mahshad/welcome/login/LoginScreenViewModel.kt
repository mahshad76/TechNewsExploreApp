package com.mahshad.welcome.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor() : ViewModel() {
    private val _userNameStateFlow: MutableStateFlow<String> = MutableStateFlow("")
    val usernameStateFlow = _userNameStateFlow.asStateFlow()

    private val _passwordStateFlow: MutableStateFlow<String> = MutableStateFlow("")
    val passwordStateFlow = _passwordStateFlow.asStateFlow()

    fun updateFlow(update: String, type: Boolean) {
        if (type) _userNameStateFlow.value = update else
            _passwordStateFlow.value = update
    }
}