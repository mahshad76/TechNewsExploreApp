package com.mahshad.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun ModifiedTab(
    isSelected: Boolean,
    pagerState: PagerState,
    index: Int,
    title: String,
    contentAlignment: Alignment,
    textColor: Color,
    icon: ImageVector,
    spacer: Int,
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
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(icon, contentDescription = "tab icon")
                    Spacer(modifier = Modifier.padding(horizontal = spacer.dp))
                    Text(title, color = textColor)
                }
            }
        }
    )
}