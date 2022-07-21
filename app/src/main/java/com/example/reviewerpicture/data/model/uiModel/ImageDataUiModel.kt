package com.example.reviewerpicture.data.model.uiModel

import com.example.reviewerpicture.data.model.networkModel.AllDataNetworkModel
import com.example.reviewerpicture.data.model.uiModel.AllDataUiModel
import com.example.reviewerpicture.data.model.uiModel.ContentType

data class ImageDataUiModel(
    override val id: String,
    override val title: String,
    override val type: ContentType = ContentType.IMAGE,
    var imageTaken: Boolean,
    var imageLink: String
) : AllDataUiModel {
    constructor(networkModel: AllDataNetworkModel): this (
        id = networkModel.id,
        title = networkModel.title,
        imageTaken = false,
        imageLink = ""
    )
}