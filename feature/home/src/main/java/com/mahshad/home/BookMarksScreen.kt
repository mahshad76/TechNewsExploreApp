package com.mahshad.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry

@Composable
fun BookMarksScreen(parentEntryProvider: () -> NavBackStackEntry) {
    val parentEntry = remember { parentEntryProvider() }
    val viewModel: NewsScreenViewModel = hiltViewModel(parentEntry)

}