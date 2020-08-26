package app.ss_n.common_ktx.extension

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun Date.toFormat(
    pattern: String = "yyyy/MM/dd HH:mm:ss",
    locale: Locale = Locale.getDefault()
): String {
    val dateFormat = SimpleDateFormat(pattern, locale)
    return try {
        dateFormat.format(this)
    } catch (e: RuntimeException) {
        ""
    }
}

fun Date.toCalender(locale: Locale = Locale.getDefault()): Calendar =
    Calendar.getInstance(locale).also {
        it.clear()
        it.time = this
    }

fun Date.toYear(locale: Locale = Locale.getDefault()): Int =
    this.toCalender(locale).get(Calendar.YEAR)

fun Date.toMonth(locale: Locale = Locale.getDefault()): Int =
    this.toCalender(locale).get(Calendar.MONTH)

fun Date.toDay(locale: Locale = Locale.getDefault()): Int =
    this.toCalender(locale).get(Calendar.DATE)

fun Date.toHour(locale: Locale = Locale.getDefault()): Int =
    this.toCalender(locale).get(Calendar.HOUR_OF_DAY)

fun Date.toMinute(locale: Locale = Locale.getDefault()): Int =
    this.toCalender(locale).get(Calendar.MINUTE)

fun Date.toSecond(locale: Locale = Locale.getDefault()): Int =
    this.toCalender(locale).get(Calendar.SECOND)
