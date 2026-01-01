package com.mahshad.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape

@Composable
fun OverlappingViews(
    backgroundModifier: Modifier,
    contentModifier: Modifier,
    backgroundShape: Shape,
    contentShape: Shape,
    background: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Card(shape = backgroundShape, modifier = backgroundModifier) {
            background()
        }
        Card(shape = contentShape, modifier = contentModifier) {
            content()
        }
    }
}