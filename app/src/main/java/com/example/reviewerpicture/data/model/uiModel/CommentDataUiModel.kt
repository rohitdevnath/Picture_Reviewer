package com.example.reviewerpicture.data.model.uiModel

import com.example.reviewerpicture.data.model.networkModel.AllDataNetworkModel
import com.example.reviewerpicture.data.model.uiModel.AllDataUiModel
import com.example.reviewerpicture.data.model.uiModel.ContentType

data class CommentDataUiModel(
    override val id: String,
    override val title: String,
    override val type: ContentType = ContentType.COMMENT,
    var isEnabled: Boolean,
    var commentText: String
) : AllDataUiModel {
    constructor(networkModel: AllDataNetworkModel): this (
        id = networkModel.id,
        title = networkModel.title,
        isEnabled = false,
        commentText = ""
    )
}