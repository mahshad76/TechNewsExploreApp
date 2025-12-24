package com.mahshad.interests

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class InterestsScreenViewModel @Inject constructor() : ViewModel() {
    private val _checkedStateFlow: MutableStateFlow<List<String>> = MutableStateFlow(emptyList())
    val checkedStateFlow = _checkedStateFlow.asStateFlow()
    fun updateCheckedState() {
        // if
    }

    suspend fun getCheckedItems(): List<String> {
        return emptyList()
    }
}