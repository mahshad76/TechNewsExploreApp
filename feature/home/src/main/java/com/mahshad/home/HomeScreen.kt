package com.mahshad.home

import android.graphics.Color
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mahshad.ui.NewsFeed

@Composable
fun HomeScreen(homeScreenViewModel: HomeScreenViewModel = hiltViewModel()) {
    Spacer(Modifier.windowInsetsTopHeight(WindowInsets.safeDrawing))
    Column() { }
//    HomeSearchBar()
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
                CircularProgressIndicator(
                    modifier = Modifier.size(64.dp),
                    color = androidx.compose.ui.graphics.Color(Color.BLACK),
                    strokeWidth = 6.dp
                )
            }
        }
}
