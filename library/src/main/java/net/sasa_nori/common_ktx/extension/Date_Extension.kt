package net.sasa_nori.common_ktx.extension

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun Date.toFormat(
    pattern: String = "yyyy/MM/dd HH:mm:ss",
    locale: Locale = Locale.getDefault()
): String? {
    val dateFormat = SimpleDateFormat(pattern, locale)
    return try {
        dateFormat.format(this)
    } catch (e: ParseException) {
        null
    }
}

fun Date.year(locale: Locale = Locale.getDefault()): Int {
    val calendar = Calendar.getInstance(locale)
    calendar.clear()
    calendar.time = this
    return calendar.get(Calendar.YEAR)
}

fun Date.month(locale: Locale = Locale.getDefault()): Int {
    val calendar = Calendar.getInstance(locale)
    calendar.clear()
    calendar.time = this
    return calendar.get(Calendar.DAY_OF_MONTH)
}

fun Date.hour(locale: Locale = Locale.getDefault()): Int {
    val calendar = Calendar.getInstance(locale)
    calendar.clear()
    calendar.time = this
    return calendar.get(Calendar.HOUR_OF_DAY)
}

fun Date.minute(locale: Locale = Locale.getDefault()): Int {
    val calendar = Calendar.getInstance(locale)
    calendar.clear()
    calendar.time = this
    return calendar.get(Calendar.MINUTE)
}

fun Date.second(locale: Locale = Locale.getDefault()): Int {
    val calendar = Calendar.getInstance(locale)
    calendar.clear()
    calendar.time = this
    return calendar.get(Calendar.SECOND)
}
