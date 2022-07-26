package com.example.reviewerpicture.utils

import android.content.Context
import com.example.reviewerpicture.BuildConfig
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.nio.charset.Charset

fun getJsonFromAsset(context: Context): String {
    var jsonString = emptyString()

    try {
        context.assets?.open(BuildConfig.INPUT_ASSET_NAME)?.let { inputStream ->
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            jsonString = String(buffer, Charset.forName("UTF-8"))
        }
    } catch (ex: IOException) {
        return emptyString()
    }

    return jsonString
}

@Throws(IOException::class)
fun saveJsonFile(context: Context, jsonString: String?) {
    val rootFolder: File? = context.getExternalFilesDir(null)

    val jsonFile = File(rootFolder, BuildConfig.OUTPUT_ASSET_NAME)
    val writer = FileWriter(jsonFile)
    writer.write(jsonString)
    writer.close()
}