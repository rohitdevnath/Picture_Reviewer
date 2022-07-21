package com.example.reviewerpicture.data.model.uiModel

data class SingleOptionUiModel(
     var isSelected: Boolean,
     val OptionText: String
) {
    constructor(networkModel: String): this (
        isSelected = false,
        OptionText = networkModel
    )
}