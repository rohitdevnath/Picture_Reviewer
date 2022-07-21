package com.example.reviewerpicture.data.model.networkModel

import kotlinx.serialization.Serializable

@Serializable
data class AllDataNetworkModel(
    val type: String,
    val id: String,
    val title: String,
    val dataMap: OptionsNetworkModel
)