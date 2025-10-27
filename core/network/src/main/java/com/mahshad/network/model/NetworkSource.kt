package com.mahshad.network.model

import kotlinx.serialization.Serializable

/**
 * Network representation of [com.mahshad.model.Source]
 */

@Serializable
data class NetworkSource(
    val id: String,
    val name: String
)