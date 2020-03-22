package net.newstyleservice.common_ktx.listener

import android.view.View
import android.view.ViewConfiguration

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
