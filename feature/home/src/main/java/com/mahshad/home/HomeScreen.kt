package com.mahshad.home

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mahshad.ui.NewsFeed

@Composable
fun HomeScreen(homeScreenViewModel: HomeScreenViewModel = hiltViewModel()) {
    //HomeSearchBar()
    val newsFeed = homeScreenViewModel.feedState.collectAsStateWithLifecycle()
    when (newsFeed.value) {
        is NewsFeed.Successful -> {
            Log.d("TAG", "HomeScreen success")
            Text((newsFeed.value as NewsFeed.Successful).news[0].content)
        }

        is NewsFeed.Error -> {
            Log.d("TAG", "Error:${(newsFeed.value as NewsFeed.Error).e.toString()}")
        }

        is NewsFeed.Loading -> {
            Log.d("TAG", "HomeScreen: loading")
        }
    }
}
