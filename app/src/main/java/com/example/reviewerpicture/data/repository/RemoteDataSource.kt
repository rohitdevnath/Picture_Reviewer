package com.example.reviewerpicture.data.repository

import com.example.reviewerpicture.data.model.networkModel.AllDataNetworkModel

interface RemoteDataSource {
    fun getData():List<AllDataNetworkModel>
}