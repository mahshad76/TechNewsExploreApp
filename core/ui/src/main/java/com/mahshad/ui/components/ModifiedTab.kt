package com.mahshad.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch

@Composable
fun ModifiedTab(
    isSelected: Boolean,
    pagerState: PagerState,
    index: Int,
    title: String,
    modifier: Modifier
) {
    val scope = rememberCoroutineScope()
    Tab(
        selected = isSelected,
        onClick = {
            scope.launch { pagerState.animateScrollToPage(index) }
        },
        text = {
            Box() {
                Text(title)
            }
        }
    )
}