package com.mahshad.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun SwipeableTabScreen(
    tabs: List<Pair<String, Painter>>,
    pageNavigator: @Composable (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(pageCount = { tabs.size })
    val scope = rememberCoroutineScope()
    Column(modifier = modifier.fillMaxSize()) {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            tabs.forEachIndexed { index, pair ->
                val isSelected = pagerState.currentPage == index
                ModifiedTab(
                    isSelected = isSelected,
                    pagerState = pagerState,
                    index = index,
                    title = pair.first,
                    contentAlignment = Alignment.Center,
                    textColor = if (isSelected) MaterialTheme.colorScheme.primary
                    else Color.DarkGray,
                    icon = pair.second,
                    spacer = 4,
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(
//                            if (isSelected) Color.Transparent
//                            else Color.Transparent
                            color = Color.Transparent
                        )
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .weight(1.0F)
                )
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            pageNavigator(page)
        }
    }
}