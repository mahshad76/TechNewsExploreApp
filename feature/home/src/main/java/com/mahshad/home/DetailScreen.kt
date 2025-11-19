package com.mahshad.home

import androidx.compose.runtime.Composable
import kotlinx.serialization.Serializable

@Serializable
data class DetailScreenRoute(val subject: String)

@Composable
fun DetailScreen(subject: String) {

}