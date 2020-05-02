package net.sasa_nori.common_ktx.listener

import android.view.View
import android.view.ViewConfiguration

/**
 * Extent [View.OnClickListener]
 * Disable Double Click
 * @property onSingleClick Callback Unit
 * @property enableDelayTime default is 500ms
 */
class SingleClickListener(
    private val onSingleClick: (View?) -> Unit,
    private val enableDelayTime: Long = ViewConfiguration.getJumpTapTimeout().toLong()
) : View.OnClickListener {

    private var diffTime: Long = 0

    override fun onClick(v: View?) {
        val now = System.currentTimeMillis()
        if (now - diffTime < enableDelayTime) return
        diffTime = now
        onSingleClick(v)
    }
}
