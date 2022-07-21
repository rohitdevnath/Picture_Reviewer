package com.example.reviewerpicture.data.repository

import com.example.reviewerpicture.data.model.uiModel.AllDataUiModel

interface CacheDataSource {
    fun setData(data: List<AllDataUiModel>): List<AllDataUiModel>
    fun getData(): List<AllDataUiModel>
    fun updateData(data: AllDataUiModel): List<AllDataUiModel>
}