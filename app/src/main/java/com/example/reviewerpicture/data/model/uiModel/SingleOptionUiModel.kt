package com.example.reviewerpicture.data.model.uiModel

data class SingleOptionUiModel(
    val parentId: String,
    val isSelected: Boolean,
    val OptionText: String
) {
    constructor(networkModel: String, parentId: String): this (
        isSelected = false,
        parentId = parentId,
        OptionText = networkModel
    )
}