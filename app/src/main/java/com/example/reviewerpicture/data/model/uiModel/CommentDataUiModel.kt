package com.example.reviewerpicture.data.model.uiModel

import com.example.reviewerpicture.data.model.networkModel.AllDataNetworkModel
import com.example.reviewerpicture.utils.emptyString

data class CommentDataUiModel(
    override val id: String,
    override val title: String,
    override val type: ContentType,
    val isEnabled: Boolean,
    val commentText: String,
) : AllDataUiModel {
    constructor(networkModel: AllDataNetworkModel, type: ContentType): this (
        id = networkModel.id,
        title = networkModel.title,
        type = type,
        isEnabled = false,
        commentText = emptyString()
    )
}