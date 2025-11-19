package com.mahshad.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import kotlinx.serialization.Serializable

@Serializable
data class DetailScreenRoute(val subject: String)

@Composable
fun DetailScreen(subject: String) {
    Text(subject)
}