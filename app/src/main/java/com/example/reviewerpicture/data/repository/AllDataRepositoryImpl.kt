package com.example.reviewerpicture.data.repository

import com.example.reviewerpicture.data.model.networkModel.AllDataNetworkModel
import com.example.reviewerpicture.data.model.uiModel.AllDataUiModel
import com.example.reviewerpicture.domain.repository.Repository

class AllDataRepositoryImpl(
    private val remoteDataSourceImpl: RemoteDataSourceImpl,
    private val cacheDataSourceImpl: CacheDataSourceImpl
): Repository {

    override fun getData(): List<AllDataNetworkModel> {
        val cacheData = cacheDataSourceImpl.getData()
        if(cacheData.isEmpty()){

        }
        return remoteDataSourceImpl.getData()
    }

    override fun submitData(data: List<AllDataUiModel>) {

    }

    override fun updateData(data: AllDataUiModel): List<AllDataUiModel> {
        return cacheDataSourceImpl.updateData(data)
    }
}