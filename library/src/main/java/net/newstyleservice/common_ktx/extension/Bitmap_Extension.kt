package net.newstyleservice.common_ktx.extension

import android.graphics.Bitmap
import android.graphics.Color
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
