package com.mahshad.Dto

import com.mahshad.model.Source
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Network representation of [Source]
 */

@Serializable
data class NetworkSource(
    @SerialName("id")
    val id: String?,
    @SerialName("name")
    val name: String?
) {
    companion object {
        val DEFAULT = NetworkSource(
            "unknown",
            "unknown"
        )
    }
}

fun NetworkSource.toSource(): Result<Source> {
    return runCatching {
        val requiredName = this.name ?: throw IllegalArgumentException(
            "The source name is " +
                    "required for each article and it is null for this item"
        )
        Source(
            id = this.id ?: "unKnown",
            name = requiredName
        )
    }
}