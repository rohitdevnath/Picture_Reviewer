package com.example.reviewerpicture.data.model.networkModel

import com.example.reviewerpicture.data.model.uiModel.CommentDataUiModel
import com.example.reviewerpicture.data.model.uiModel.ImageDataUiModel
import com.example.reviewerpicture.data.model.uiModel.OptionsDataUiModel
import com.example.reviewerpicture.utils.emptyString
import kotlinx.serialization.Serializable

@Serializable
data class SubmitDataNetworkModel(
    val type: String,
    val id: String,
    val title: String,
    val userResponse: String,
    val dataMap: OptionsNetworkModel
){
    constructor(uiModel : ImageDataUiModel) : this(
        type = uiModel.type.type,
        id = uiModel.id,
        title = uiModel.title,
        userResponse = uiModel.imagePath,
        dataMap = OptionsNetworkModel(listOf())
    )
    constructor(uiModel : CommentDataUiModel) : this(
        type = uiModel.type.type,
        id = uiModel.id,
        title = uiModel.title,
        userResponse = if(uiModel.isEnabled) uiModel.commentText else emptyString(),
        dataMap = OptionsNetworkModel(listOf())
    )

    constructor(uiModel : OptionsDataUiModel) : this(
        type = uiModel.type.type,
        id = uiModel.id,
        title = uiModel.title,
        userResponse = uiModel.options.firstOrNull{it.isSelected}?.OptionText ?: emptyString(),
        dataMap = OptionsNetworkModel(options = uiModel.options.map { it.OptionText })
    )

}