package com.example.reviewerpicture.data.model.uiModel

import com.example.reviewerpicture.data.model.networkModel.AllDataNetworkModel
import com.example.reviewerpicture.data.model.uiModel.AllDataUiModel
import com.example.reviewerpicture.data.model.uiModel.ContentType

data class OptionsDataUiModel(
    override val id: String,
    override val title: String,
    override val type: ContentType = ContentType.OPTION,
    var selectedOption: String,
    val options: List<SingleOptionUiModel>
) : AllDataUiModel {
    constructor(networkModel: AllDataNetworkModel): this (
        id = networkModel.id,
        title = networkModel.title,
        selectedOption = "",
        options = networkModel.dataMap.options.map(::SingleOptionUiModel)
    )

}