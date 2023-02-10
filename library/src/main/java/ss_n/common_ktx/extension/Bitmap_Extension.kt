package ss_n.common_ktx.extension

import android.Manifest
import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.graphics.Color
import android.util.Log
import androidx.annotation.RequiresPermission
import java.io.File
import java.io.FileOutputStream
import kotlin.math.abs
import kotlin.math.min
import kotlin.math.roundToInt

/**
 *  Sepia image processing
 *
 * @return Processed image
 */
fun Bitmap.toSepia(): Bitmap {
    val pixels = IntArray(width * height)
    getPixels(pixels, 0, width, 0, 0, width, height)

    for (i in pixels.indices) {
        var red = pixels[i] and 0x00FF0000 shr 16
        var green = pixels[i] and 0x0000FF00 shr 8
        var blue = pixels[i] and 0x000000FF
        val gray = 77 * red + 150 * green + 29 * blue shr 8
        blue = min(255, gray.toFloat().roundToInt())
        green = blue
        red = green
        red *= 1
        green = abs(green * 0.9).toInt()
        blue = abs(blue * 0.7).toInt()
        pixels[i] = Color.rgb(red, green, blue)
    }
    return Bitmap.createBitmap(pixels, 0, width, width, height, Bitmap.Config.RGB_565)
}

/**
 * Bitmap save
 *
 * @param filePath File Path
 * @param format default: JPEG
 * @param quality default: 100
 * @param isErrorLog default: false
 * @return true:File saved
 */
@RequiresPermission(
    allOf = [Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE]
)
fun Bitmap.save(
    filePath: String,
    format: CompressFormat = CompressFormat.JPEG,
    quality: Int = 100,
    isErrorLog: Boolean = false
): Boolean {
    if (filePath.isEmpty()) return false

    return try {
        val imageFile = File(filePath)
        val outputStream = FileOutputStream(imageFile)
        compress(format, quality, outputStream)
        outputStream.close()
        imageFile.exists()
    } catch (exception: Exception) {
        if (isErrorLog) {
            Log.w("bitmap save", exception.message, exception)
        }
        false
    }
}
