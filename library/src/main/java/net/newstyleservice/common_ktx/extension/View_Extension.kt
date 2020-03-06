package net.newstyleservice.common_ktx.extension

import android.graphics.Point
import android.view.View
import net.newstyleservice.common_ktx.listener.SingleClickListener

fun View.setOnSingleClickListener(listener: (View?) -> Unit) {
    setOnClickListener(SingleClickListener(listener))
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.inVisible() {
    visibility = View.INVISIBLE
}

fun View.changeVisible(isVisible: Boolean) {
    when {
        isVisible -> visible()
        else -> gone()
    }
}

fun View.getViewSize(): Point {
    val point = Point(0, 0)
    point.set(width, height)
    return point
}