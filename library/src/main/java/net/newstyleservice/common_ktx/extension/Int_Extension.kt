package net.newstyleservice.common_ktx.extension

import android.content.Context
import android.content.res.Resources
import android.content.res.Resources.NotFoundException

/**
 * Get Dimension Pixel Size
 * @see [Resources.getDimensionPixelSize]
 *
 * @param context [Context]
 * @return getDimensionPixelSize
 */
fun Int.getPixelSize(context: Context): Int {
    return try {
        context.resources.getDimensionPixelSize(this)
    } catch (exception: NotFoundException) {
        0
    }
}

/**
 * Get Dimension Offset
 * @see [Resources.getDimensionPixelOffset]
 *
 * @param context [Context]
 * @return getDimensionPixelOffset
 */
fun Int.getPixelOffset(context: Context): Int {
    return try {
        context.resources.getDimensionPixelOffset(this)
    } catch (exception: NotFoundException) {
        0
    }
}