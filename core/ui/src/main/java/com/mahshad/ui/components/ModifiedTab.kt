package com.mahshad.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.launch

@Composable
fun ModifiedTab(
    isSelected: Boolean,
    pagerState: PagerState,
    index: Int,
    title: String,
    contentAlignment: Alignment,
    textColor: Color,
    modifier: Modifier
) {
    val scope = rememberCoroutineScope()
    Tab(
        selected = isSelected,
        onClick = {
            scope.launch { pagerState.animateScrollToPage(index) }
        },
        text = {
            Box(modifier = modifier, contentAlignment = contentAlignment) {
                Text(title, color = textColor)
            }
        }
    )
}