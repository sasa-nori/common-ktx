package net.newstyleservice.common_ktx.extension

import java.io.File
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * String parse to Date
 *
 * @param pattern default = "yyyy/MM/dd HH:mm:ss"
 * @param locale default = device default
 * @return parsed Date
 */
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

/**
 * Crate File from String
 * If there is no directory, create it together
 *
 * @return Created Files
 */
fun String.makeFile(): File = File(this).apply {
    this.mkdirs()
}

/**
 * Move File
 *
 * @param target targetFilePath
 * @return Moved File
 */
fun String.moveFile(target: String): File? {
    val preFile = File(target)
    if (!preFile.exists()) return null

    val movedFile = File(this)
    return when (preFile.renameTo(movedFile)) {
        true -> movedFile
        else -> null
    }
}

fun String.matchPattern(
    pattern: String,
    errorMessage: String = "",
    error: ((String) -> Unit?)? = null
): Boolean {
    return pattern.toRegex().matchEntire(this)?.let {
        true
    } ?: run {
        error?.let {
            it(errorMessage)
        }
        false
    }
}

fun String.isMailAddress(
    errorMessage: String = "",
    error: ((String) -> Unit?)? = null
): Boolean {
    val pattern = "^[a-zA-Z0-9\\._\\-\\+]+@[a-zA-Z0-9_\\-]+\\.[a-zA-Z\\.]+[a-zA-Z]\$"
    return matchPattern(pattern, errorMessage, error)
}

fun String.isPhoneNumber(errorMessage: String = "", error: ((String) -> Unit?)? = null): Boolean {
    val pattern = "\\d{10}|\\d{11}"
    return matchPattern(pattern, errorMessage, error)
}

fun String.isNumber(errorMessage: String = "", error: ((String) -> Unit?)? = null): Boolean {
    val pattern = "^[0-9].*?\$"
    return matchPattern(pattern, errorMessage, error)
}

fun String.isInt(errorMessage: String = "", error: ((String) -> Unit?)? = null): Boolean {
    val pattern = "^[0-9]{11}\$"
    return matchPattern(pattern, errorMessage, error)
}
