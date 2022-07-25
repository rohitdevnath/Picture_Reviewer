package com.example.reviewerpicture.domain.usecase

import com.example.reviewerpicture.data.model.uiModel.AllDataUiModel
import com.example.reviewerpicture.data.model.uiModel.CommentDataUiModel
import com.example.reviewerpicture.data.model.uiModel.ImageDataUiModel
import com.example.reviewerpicture.data.model.uiModel.SingleOptionUiModel
import com.example.reviewerpicture.domain.repository.Repository

class UpdateDataUseCase(private val repository: Repository) {

    fun updateImageData(model: ImageDataUiModel):List<AllDataUiModel> = repository.updateImageData(model)

    fun removeImage(model: ImageDataUiModel):List<AllDataUiModel> = repository.removeImageData(model)

    fun updateCommentText(model: CommentDataUiModel):List<AllDataUiModel> = repository.updateCommentText(model)

    fun updateCommentToggle(model: CommentDataUiModel):List<AllDataUiModel> = repository.updateCommentToggle(model)

    fun updateOption(model: SingleOptionUiModel):List<AllDataUiModel> = repository.updateOptionsData(model)

}