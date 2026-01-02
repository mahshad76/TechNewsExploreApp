package com.mahshad.model

import kotlinx.serialization.Serializable

@Serializable
data class Source(
    val id: String,
    val name: String
) {
    companion object {
        val DEFAULT = Source(
            id = "unknown",
            name = "unknown"
        )
    }
}