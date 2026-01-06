package com.mahshad.home.ui

import androidx.compose.runtime.Composable
import kotlinx.serialization.Serializable

@Serializable
data class ExternalArticleScreenRoute(val url: String)

@Composable
fun ExternalArticleScreen(url: String) {
}