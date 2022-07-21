package com.example.reviewerpicture.data.model.networkModel

import kotlinx.serialization.Serializable

@Serializable
data class OptionsNetworkModel (
    val options: List<String> = listOf()
)