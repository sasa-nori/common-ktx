package net.newstyleservice.common_ktx

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import java.io.IOException
import kotlin.math.min

/**
 * https://qiita.com/masahide318/items/e9afbbbf6747bb091f63 参照
 */
class BitmapResizer(private val context: Context) {
    @Throws(IOException::class)
    fun resize(uri: Uri?, mMaxWidth: Int, mMaxHeight: Int): Bitmap? {
        val bitmap: Bitmap?
        val decodeOptions = BitmapFactory.Options()
        decodeOptions.inJustDecodeBounds = true
        var inputStream = context.contentResolver.openInputStream(uri!!)
        BitmapFactory.decodeStream(inputStream, null, decodeOptions)
        inputStream!!.close()
        val actualWidth = decodeOptions.outWidth
        val actualHeight = decodeOptions.outHeight
        val desiredWidth = getResizedDimension(
            mMaxWidth, mMaxHeight,
            actualWidth, actualHeight
        )
        val desiredHeight = getResizedDimension(
            mMaxHeight, mMaxWidth,
            actualHeight, actualWidth
        )
        decodeOptions.inJustDecodeBounds = false
        decodeOptions.inSampleSize = findBestSampleSize(
            actualWidth,
            actualHeight,
            desiredWidth,
            desiredHeight
        )
        inputStream = context.contentResolver.openInputStream(uri)
        val tempBitmap = BitmapFactory.decodeStream(inputStream, null, decodeOptions)
        inputStream!!.close()
        if (tempBitmap != null && (tempBitmap.width > desiredWidth ||
                    tempBitmap.height > desiredHeight)
        ) {
            bitmap = Bitmap.createScaledBitmap(
                tempBitmap,
                desiredWidth, desiredHeight, true
            )
            tempBitmap.recycle()
        } else {
            bitmap = tempBitmap
        }
        return bitmap
    }

    private fun getResizedDimension(
        maxPrimary: Int,
        maxSecondary: Int,
        actualPrimary: Int,
        actualSecondary: Int
    ): Int {
        if (maxPrimary == 0 && maxSecondary == 0) {
            return actualPrimary
        }
        if (maxPrimary == 0) {
            val ratio =
                maxSecondary.toDouble() / actualSecondary.toDouble()
            return (actualPrimary * ratio).toInt()
        }
        if (maxSecondary == 0) {
            return maxPrimary
        }
        val ratio = actualSecondary.toDouble() / actualPrimary.toDouble()
        var resized = maxPrimary
        if (resized * ratio < maxSecondary) {
            resized = (maxSecondary / ratio).toInt()
        }
        return resized
    }

    private fun findBestSampleSize(
        actualWidth: Int,
        actualHeight: Int,
        desiredWidth: Int,
        desiredHeight: Int
    ): Int {
        val wr = actualWidth.toDouble() / desiredWidth
        val hr = actualHeight.toDouble() / desiredHeight
        val ratio = min(wr, hr)
        var n = 1.0f
        while (n * 2 <= ratio) {
            n *= 2f
        }
        return n.toInt()
    }
}
