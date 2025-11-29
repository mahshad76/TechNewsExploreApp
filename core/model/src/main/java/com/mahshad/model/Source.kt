package com.mahshad.model

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