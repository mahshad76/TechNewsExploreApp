package com.mahshad.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Network representation of [com.mahshad.model.Source]
 */

@Serializable
data class NetworkSource(
    @SerialName("id")
    val id: String?,
    @SerialName("name")
    val name: String?
)