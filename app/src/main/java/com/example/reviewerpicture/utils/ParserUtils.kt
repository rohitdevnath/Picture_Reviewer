package com.example.reviewerpicture.utils

import android.content.Context
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.nio.charset.Charset

fun getJsonFromAsset(context: Context): String {
    var jsonString = emptyString()

    try {
        context.assets?.open("raw_data.json")?.let { inputStream ->
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

    val jsonFile = File(rootFolder, "rohit.json")
    val writer = FileWriter(jsonFile)
    writer.write(jsonString)
    writer.close()
}