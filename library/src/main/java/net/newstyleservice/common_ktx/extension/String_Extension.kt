package net.newstyleservice.common_ktx.extension

import java.io.File
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * String to Decimal Text
 *
 * @return example 10000 to 10,000
 */
fun String.decimalText(): String {
    return String.format("%,d", this)
}

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
