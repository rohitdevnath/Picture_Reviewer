package com.example.reviewerpicture.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ThumbnailUtils
import androidx.exifinterface.media.ExifInterface


private const val THUMBNAIL_SIZE = 400

fun getBitmapImage(imagePath: String):Bitmap{
    return checkForRotation(imagePath = imagePath, bitmap = BitmapFactory.decodeFile(imagePath))
}

fun getThumbnailImage(imagePath: String):Bitmap{
    return checkForRotation(imagePath = imagePath,
        bitmap = ThumbnailUtils.extractThumbnail( BitmapFactory.decodeFile(imagePath),
            THUMBNAIL_SIZE,
            THUMBNAIL_SIZE
        )
    )
}


private fun checkForRotation(imagePath: String, bitmap: Bitmap): Bitmap{
    val ei = ExifInterface(imagePath)
    val orientation: Int = ei.getAttributeInt(
        ExifInterface.TAG_ORIENTATION,
        ExifInterface.ORIENTATION_UNDEFINED
    )
    return when (orientation) {
        ExifInterface.ORIENTATION_ROTATE_90 -> rotateImage(bitmap, 90f)
        ExifInterface.ORIENTATION_ROTATE_180 -> rotateImage(bitmap, 180f)
        ExifInterface.ORIENTATION_ROTATE_270 -> rotateImage(bitmap, 270f)
        ExifInterface.ORIENTATION_NORMAL -> bitmap
        else -> bitmap
    }
}

private fun rotateImage(source: Bitmap, angle: Float): Bitmap {
    val matrix = Matrix()
    matrix.postRotate(angle)
    return Bitmap.createBitmap(
        source, 0, 0, source.width, source.height,
        matrix, true
    )
}