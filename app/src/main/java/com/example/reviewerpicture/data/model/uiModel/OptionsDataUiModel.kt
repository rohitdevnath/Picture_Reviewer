package com.example.reviewerpicture.data.model.uiModel

import com.example.reviewerpicture.data.model.networkModel.AllDataNetworkModel

data class OptionsDataUiModel(
    override val id: String,
    override val title: String,
    override val type: ContentType,
    val options: List<SingleOptionUiModel>
) : AllDataUiModel {
    constructor(networkModel: AllDataNetworkModel, type: ContentType): this (
        id = networkModel.id,
        title = networkModel.title,
        type = type,
        options = networkModel.dataMap.options.map { SingleOptionUiModel(it,networkModel.id) }
    )

}