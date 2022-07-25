package com.example.reviewerpicture.data.model.networkModel

import kotlinx.serialization.Serializable

@Serializable
data class AllDataNetworkModel(
    val type: String,
    val id: String,
    val title: String,
    val dataMap: OptionsNetworkModel
){
    companion object{
        const val ARG_TYPE_PHOTO = "PHOTO"
        const val ARG_TYPE_COMMENT = "COMMENT"
        const val ARG_TYPE_OPTIONS = "SINGLE_CHOICE"

    }
}