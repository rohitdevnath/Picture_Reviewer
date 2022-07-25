package com.example.reviewerpicture.data.repository

import com.example.reviewerpicture.data.model.networkModel.AllDataNetworkModel
import io.reactivex.Single

interface RemoteDataSource {
    fun getData(): Single<List<AllDataNetworkModel>>
}