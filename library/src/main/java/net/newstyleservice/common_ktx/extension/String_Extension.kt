package net.newstyleservice.common_ktx.extension

import androidx.annotation.Nullable
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun String.decimalText() = String.format("%,d", this)

@Nullable
fun String.toDate(
    pattern: String = "yyyy/MM/dd HH:mm:ss",
    locale: Locale = Locale.getDefault()
): Date? {
    val dateFormat = SimpleDateFormat(pattern, locale)
    return try {
        dateFormat.parse(this)
    } catch (e: ParseException) {
        null
    }
}
