package net.newstyleservice.common_ktx.listener

import android.view.View
import android.view.ViewConfiguration

class SingleClickListener(private val onSingleClick: (View?) -> Unit) : View.OnClickListener {

    private var diffTime: Long = 0

    override fun onClick(v: View?) {
        val now = System.currentTimeMillis()
        if (diffTime + ENABLE_DELAY_TIME < now) return
        diffTime = now
        onSingleClick(v)
    }

    companion object {
        private val ENABLE_DELAY_TIME: Long = ViewConfiguration.getJumpTapTimeout().toLong()
    }
}