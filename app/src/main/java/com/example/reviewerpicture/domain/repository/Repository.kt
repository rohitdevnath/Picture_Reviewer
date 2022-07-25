package com.example.reviewerpicture.domain.repository

import com.example.reviewerpicture.data.model.uiModel.AllDataUiModel
import com.example.reviewerpicture.data.model.uiModel.CommentDataUiModel
import com.example.reviewerpicture.data.model.uiModel.ImageDataUiModel
import com.example.reviewerpicture.data.model.uiModel.SingleOptionUiModel
import io.reactivex.Single

interface Repository {
    fun getData(): Single<List<AllDataUiModel>>
    fun submitData(data: List<AllDataUiModel>)
    fun updateOptionsData(data: SingleOptionUiModel): List<AllDataUiModel>
    fun updateCommentText(data: CommentDataUiModel): List<AllDataUiModel>
    fun updateCommentToggle(data: CommentDataUiModel): List<AllDataUiModel>
    fun updateImageData(data: ImageDataUiModel): List<AllDataUiModel>
    fun removeImageData(data: ImageDataUiModel): List<AllDataUiModel>
}