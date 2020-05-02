package net.sasa_nori.common_ktx.extension

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import net.sasa_nori.common_ktx.BitmapResizer
import java.io.IOException

fun Uri.resizeBitmap(context: Context, width: Int, height: Int): Bitmap? = try {
    BitmapResizer(context).resize(this, width, height)
} catch (e: IOException) {
    null
}
