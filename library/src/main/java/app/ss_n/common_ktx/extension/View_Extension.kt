package app.ss_n.common_ktx.extension

import android.graphics.Point
import android.view.View
import android.view.ViewConfiguration
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import android.widget.GridLayout
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.RelativeLayout
import android.widget.TableLayout
import androidx.annotation.DimenRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.getSystemService
import ss_n.common_ktx.listener.SingleClickListener

/**
 * Set On Single Click Listener
 * Interval is 500ms
 *
 * @param enableDelayTime [ViewConfiguration.getJumpTapTimeout] by 500ms
 * @param listener [View.OnClickListener]
 */
fun View.setOnSingleClickListener(
    enableDelayTime: Long = ViewConfiguration.getJumpTapTimeout().toLong(),
    listener: (View?) -> Unit
) {
    setOnClickListener(SingleClickListener(listener, enableDelayTime))
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

/**
 * Set Margin to View
 *
 * @param left value is pixel
 * @param top value is pixel
 * @param right value is pixel
 * @param bottom value is pixel
 */
fun View.setMargin(
    left: Int? = null,
    top: Int? = null,
    right: Int? = null,
    bottom: Int? = null
) {
    parent?.let {
        when (it) {
            is LinearLayout -> (layoutParams as LinearLayout.LayoutParams)
            is RelativeLayout -> (layoutParams as RelativeLayout.LayoutParams)
            is FrameLayout -> (layoutParams as FrameLayout.LayoutParams)
            is GridLayout -> (layoutParams as GridLayout.LayoutParams)
            is TableLayout -> (layoutParams as TableLayout.LayoutParams)
            is RadioGroup -> (layoutParams as RadioGroup.LayoutParams)
            is CoordinatorLayout -> (layoutParams as CoordinatorLayout.LayoutParams)
            is ConstraintLayout -> (layoutParams as ConstraintLayout.LayoutParams)
            else -> null
        }?.let { params ->
            left?.let { params.leftMargin = left }
            top?.let { params.topMargin = top }
            right?.let { params.rightMargin = right }
            bottom?.let { params.bottomMargin = bottom }
            layoutParams = params
        }
    }
}

/**
 * Set Margin to View
 *
 * @param leftRes value is DimensRes
 * @param topRes value is DimensRes
 * @param rightRes value is DimensRes
 * @param bottomRes value is DimensRes
 */
fun View.setMarginRes(
    @DimenRes leftRes: Int? = null,
    @DimenRes topRes: Int? = null,
    @DimenRes rightRes: Int? = null,
    @DimenRes bottomRes: Int? = null
) {
    setMargin(
        left = leftRes?.getPixelSize(context),
        top = topRes?.getPixelSize(context),
        right = rightRes?.getPixelSize(context),
        bottom = bottomRes?.getPixelSize(context)
    )
}
