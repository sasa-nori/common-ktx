package net.newstyleservice.common_ktx.extension

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun Date.toString(
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

fun Date.getYear(locale: Locale = Locale.getDefault()): Int {
    val calendar = Calendar.getInstance(locale)
    calendar.clear()
    calendar.time = this
    return calendar.get(Calendar.YEAR)
}

fun Date.getMonth(locale: Locale = Locale.getDefault()): Int {
    val calendar = Calendar.getInstance(locale)
    calendar.clear()
    calendar.time = this
    return calendar.get(Calendar.DAY_OF_MONTH)
}

fun Date.getHour(locale: Locale = Locale.getDefault()): Int {
    val calendar = Calendar.getInstance(locale)
    calendar.clear()
    calendar.time = this
    return calendar.get(Calendar.HOUR_OF_DAY)
}

fun Date.getMinute(locale: Locale = Locale.getDefault()): Int {
    val calendar = Calendar.getInstance(locale)
    calendar.clear()
    calendar.time = this
    return calendar.get(Calendar.MINUTE)
}

fun Date.getSecond(locale: Locale = Locale.getDefault()): Int {
    val calendar = Calendar.getInstance(locale)
    calendar.clear()
    calendar.time = this
    return calendar.get(Calendar.SECOND)
}