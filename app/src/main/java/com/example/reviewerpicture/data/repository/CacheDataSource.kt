package com.example.reviewerpicture.data.repository

import com.example.reviewerpicture.data.model.uiModel.AllDataUiModel
import com.example.reviewerpicture.data.model.uiModel.CommentDataUiModel
import com.example.reviewerpicture.data.model.uiModel.ImageDataUiModel
import com.example.reviewerpicture.data.model.uiModel.SingleOptionUiModel

interface CacheDataSource {
    fun setData(data: List<AllDataUiModel>): List<AllDataUiModel>
    fun getData(): List<AllDataUiModel>
    fun updateOptionDataInCache(data: SingleOptionUiModel): List<AllDataUiModel>
    fun updateCommentToggleInCache(data: CommentDataUiModel): List<AllDataUiModel>
    fun updateCommentTextInCache(data: CommentDataUiModel): List<AllDataUiModel>
    fun updateImageDataInCache(data: ImageDataUiModel): List<AllDataUiModel>
    fun removeImageDataInCache(data: ImageDataUiModel): List<AllDataUiModel>

}