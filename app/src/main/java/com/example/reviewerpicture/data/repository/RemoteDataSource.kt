package com.example.reviewerpicture.data.repository

import com.example.reviewerpicture.data.model.networkModel.AllDataNetworkModel
import com.example.reviewerpicture.data.model.networkModel.SubmitDataNetworkModel
import io.reactivex.Single

interface RemoteDataSource {
    fun getData(): Single<List<AllDataNetworkModel>>
    fun submitData(data : List<SubmitDataNetworkModel>): Single<Unit>
}