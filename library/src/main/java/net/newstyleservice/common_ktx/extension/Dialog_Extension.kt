package net.newstyleservice.common_ktx.extension

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import androidx.annotation.LayoutRes

/**
 * Crate FullScreen Dialog
 * Can be used in DialogFragment
 *
 * @param layoutRes Layout Res
 * @param isCancelable true = setCancelable is true
 * @param isCanceledOnTouchOutside true = setCanceledOnTouchOutside is true
 */
fun Dialog.createFullScreen(
    @LayoutRes layoutRes: Int?,
    isCancelable: Boolean = true,
    isCanceledOnTouchOutside: Boolean = true
) {
    requestWindowFeature(Window.FEATURE_NO_TITLE)
    layoutRes?.let {
        setContentView(LayoutInflater.from(context).inflate(it, null, false))
    }
    window?.let {
        it.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        it.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
    setCancelable(isCancelable)
    setCanceledOnTouchOutside(isCanceledOnTouchOutside)
}
