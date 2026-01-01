package com.mahshad.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
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
        Card(shape = backgroundShape, modifier = backgroundModifier.weight(30.0F)) {
            background()
        }
        Card(
            shape = contentShape,
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            modifier = contentModifier.weight(70.0F)
        ) {
            content()
        }
    }
}