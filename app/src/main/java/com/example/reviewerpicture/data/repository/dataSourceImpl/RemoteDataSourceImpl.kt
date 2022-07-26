package com.example.reviewerpicture.data.repository.dataSourceImpl

import android.content.Context
import com.example.reviewerpicture.data.model.networkModel.AllDataNetworkModel
import com.example.reviewerpicture.data.model.networkModel.SubmitDataNetworkModel
import com.example.reviewerpicture.data.repository.dataSource.RemoteDataSource
import com.example.reviewerpicture.utils.getJsonFromAsset
import com.example.reviewerpicture.utils.saveJsonFile
import io.reactivex.Single
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class RemoteDataSourceImpl(private val context: Context) : RemoteDataSource {

    override fun getData(): Single<List<AllDataNetworkModel>> {
        val encoded = getJsonFromAsset(context)
        if(encoded.isEmpty())
            return Single.error(IllegalArgumentException("Proper data not found"))

        return Single.just(Json.decodeFromString(encoded))
    }

    override fun submitData(data: List<SubmitDataNetworkModel>): Single<Unit> {
        val jsonString = Json.encodeToString(data)
        return Single.just(
            saveJsonFile(context, jsonString)
        )
    }
}