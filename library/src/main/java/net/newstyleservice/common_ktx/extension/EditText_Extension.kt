package net.newstyleservice.common_ktx.extension

import android.widget.EditText

fun EditText.getString(): String = text?.toString() ?: ""