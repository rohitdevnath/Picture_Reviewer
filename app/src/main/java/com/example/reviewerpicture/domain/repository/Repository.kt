package com.example.reviewerpicture.domain.repository

import com.example.reviewerpicture.data.model.networkModel.AllDataNetworkModel
import com.example.reviewerpicture.data.model.uiModel.AllDataUiModel

interface Repository {
    fun getData(): List<AllDataNetworkModel>
    fun submitData(data: List<AllDataUiModel>)
    fun updateData(data: AllDataUiModel): List<AllDataUiModel>
}