package com.example.reviewerpicture.data.model.uiModel

import android.graphics.Bitmap
import com.example.reviewerpicture.data.model.networkModel.AllDataNetworkModel
import com.example.reviewerpicture.utils.emptyString

data class ImageDataUiModel(
    override val id: String,
    override val title: String,
    override val type: ContentType,
    val imagePath: String,
    val imageTaken: Boolean,
    val imageLink: Bitmap?
) : AllDataUiModel {
    constructor(networkModel: AllDataNetworkModel, contentType: ContentType): this (
        id = networkModel.id,
        title = networkModel.title,
        type = contentType,
        imagePath = emptyString(),
        imageTaken = false,
        imageLink = null
    )
}