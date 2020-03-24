package net.newstyleservice.common_ktx.extension

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

/**
 * Layout Inflate
 *
 * @param layoutInflater LayoutInflater
 * @param layout Layout Res example R.id.text
 * @param attachToRoot true is root Attach
 * @return Inflated View
 */
fun ViewGroup.inflate(
    layoutInflater: LayoutInflater? = null,
    @LayoutRes layout: Int,
    attachToRoot: Boolean = false
): View {
    val inflater = layoutInflater ?: LayoutInflater.from(
        context
    )
    return inflater.inflate(layout, this, attachToRoot)
}
