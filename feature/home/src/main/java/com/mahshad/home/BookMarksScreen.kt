package com.mahshad.home

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry

@Composable
fun BookMarksScreen(
    ParentEntryProvider: () -> NavBackStackEntry,
    viewModel: NewsScreenViewModel = hiltViewModel(ParentEntryProvider())
) {
}