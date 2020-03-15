package net.newstyleservice.common_ktx.extension

import android.graphics.Point
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.getSystemService
import net.newstyleservice.common_ktx.listener.SingleClickListener

/**
 * Set On Single Click Listener
 * Interval is 500ms
 *
 * @param listener [View.OnClickListener]
 */
fun View.setOnSingleClickListener(listener: (View?) -> Unit) {
    setOnClickListener(SingleClickListener(listener))
}

/**
 * Set Visibility to [View.VISIBLE]
 */
fun View.visible() {
    visibility = View.VISIBLE
}

/**
 * Set Visibility to [View.GONE]
 */
fun View.gone() {
    visibility = View.GONE
}

/**
 * Set Visibility to [View.INVISIBLE]
 */
fun View.inVisible() {
    visibility = View.INVISIBLE
}

/**
 * Change Visibility [View.VISIBLE] or [View.GONE]
 *
 * @param isVisible true = [View.VISIBLE], false =[View.GONE]
 */
fun View.changeVisible(isVisible: Boolean) {
    when {
        isVisible -> visible()
        else -> gone()
    }
}

/**
 * Get View Size
 *
 * @return View size
 */
fun View.getViewSize(): Point {
    val point = Point(0, 0)
    point.set(width, height)
    return point
}

/**
 * Hide to Keyboard
 */
fun View.hideKeyboard() {
    val inputMethodManager: InputMethodManager = context.getSystemService()!!
    inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    clearFocus()
}
