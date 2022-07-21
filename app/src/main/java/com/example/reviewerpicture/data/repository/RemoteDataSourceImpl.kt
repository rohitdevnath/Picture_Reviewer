package com.example.reviewerpicture.data.repository

import android.content.Context
import com.example.reviewerpicture.data.model.networkModel.AllDataNetworkModel
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.IOException
import java.nio.charset.Charset

class RemoteDataSourceImpl(private val context: Context):RemoteDataSource {

    override fun getData(): List<AllDataNetworkModel> {
        val encoded = getJsonFromAsset()
        return Json.decodeFromString(encoded)
    }

    private fun getJsonFromAsset() : String {
        var jsonString = ""

        try{
            context.assets?.open("raw_data.json")?.let { inputStream ->
                val size = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                inputStream.close()
                jsonString = String(buffer, Charset.forName("UTF-8"))
            }
        } catch (ex: IOException){
            return ""
        }

        return jsonString
    }
}