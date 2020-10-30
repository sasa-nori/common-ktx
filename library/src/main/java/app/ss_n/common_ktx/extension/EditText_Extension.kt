package app.ss_n.common_ktx.extension

import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.content.getSystemService
import androidx.core.os.postDelayed

/**
 * Get to EditText Input Data
 *
 * @return Empty or Input Data
 */
fun EditText.getString(): String = text?.toString() ?: ""

/**
 * Show Keyboard
 *
 * @param delayTime default = 0
 */
fun EditText.showKeyboard(delayTime: Long = 0) {
    handler.postDelayed(delayTime) {
        val inputMethodManager: InputMethodManager = context.getSystemService()!!
        inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }
}
