package net.sasa_nori.common_ktx.extension

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresPermission
import androidx.core.os.bundleOf
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import net.sasa_nori.common_ktx.HttpClient
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
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
@RequiresPermission(
    allOf = [Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE]
)
fun String.makeFile(): File = File(this).apply {
    this.mkdirs()
}

/**
 * Move File
 *
 * @param target targetFilePath
 * @return Moved File
 */
@RequiresPermission(
    allOf = [Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE]
)
fun String.moveFile(target: String): File? {
    val preFile = File(target)
    if (!preFile.exists()) return null

    val movedFile = File(this)
    return when (preFile.renameTo(movedFile)) {
        true -> movedFile
        else -> null
    }
}

/**
 * Has File
 *
 * @return true: File exist
 */
@RequiresPermission(
    allOf = [Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE]
)
fun String.hasFile(): Boolean {
    if (isEmpty()) return false

    val target = File(this)
    return target.exists() && target.isFile
}

/**
 * Get File Size
 *
 * @param sizeFormat default KB size format
 * @return File Size
 */
@RequiresPermission(
    allOf = [Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE]
)
fun String.getFileSize(sizeFormat: Long = 1024): Long {
    if (isEmpty()) return 0

    val target = File(this)

    if (!target.exists() || !target.isFile) return 0

    return target.length() / sizeFormat
}

/**
 * Regex String
 *
 * @param pattern regex pattern
 * @param errorMessage error message
 * @param error response method
 * @return true:matched
 */
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

/**
 * Matched MailAddress
 *
 * @param errorMessage error message
 * @param error response method
 * @return true:matched
 */
fun String.isMailAddress(
    errorMessage: String = "",
    error: ((String) -> Unit?)? = null
): Boolean {
    val pattern = "^[a-zA-Z0-9\\._\\-\\+]+@[a-zA-Z0-9_\\-]+\\.[a-zA-Z\\.]+[a-zA-Z]\$"
    return matchPattern(pattern, errorMessage, error)
}

/**
 * Matched PhoneNumber
 *
 * @param errorMessage error message
 * @param error response method
 * @return true:matched
 */
fun String.isPhoneNumber(errorMessage: String = "", error: ((String) -> Unit?)? = null): Boolean {
    val pattern = "\\d{10}|\\d{11}"
    return matchPattern(pattern, errorMessage, error)
}

/**
 * Matched Number
 *
 * @param errorMessage error message
 * @param error response method
 * @return true:matched
 */
fun String.isNumber(errorMessage: String = "", error: ((String) -> Unit?)? = null): Boolean {
    val pattern = "^[0-9].*?\$"
    return matchPattern(pattern, errorMessage, error)
}

/**
 * Matched Int
 *
 * @param errorMessage error message
 * @param error response method
 * @return true:matched
 */
fun String.isInt(errorMessage: String = "", error: ((String) -> Unit?)? = null): Boolean {
    val pattern = "^[0-9]{11}\$"
    return matchPattern(pattern, errorMessage, error)
}

/**
 * Show Toast
 *
 * @param context Context
 * @param toastLength Toast Length
 */
fun String.showToast(context: Context, toastLength: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, this, toastLength).show()
}

/**
 * Create Bundle
 *
 * @param value value
 * @return [Bundle]
 */
fun String.toBundleKey(value: Any): Bundle = bundleOf(this to value)

/**
 * Create MutableMap
 *
 * @param value value
 * @return [MutableMap]
 */
fun String.toMutableMapKey(value: Any): MutableMap<String, Any> = mutableMapOf(this to value)

/**
 * Create Map
 *
 * @param value value
 * @return [Map]
 */
fun String.toMapKey(value: Any): Map<String, Any> = mapOf(this to value)

/**
 * Create Retrofit Service
 *
 * @param T Service interface class
 * @param service Service interface class
 * @param client OkHttpClient :default [HttpClient]
 * @param converterFactory Json Parser Converter Factory :default [MoshiConverterFactory]
 * @return Service interface class Instance
 */
fun <T> String.createRetrofitService(
    service: Class<T>,
    client: OkHttpClient? = HttpClient.defaultClient,
    converterFactory: Converter.Factory? = MoshiConverterFactory.create(
        Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    )
): T {
    return Retrofit.Builder().apply {
        baseUrl(this@createRetrofitService)
        client?.also { client(it) }
        converterFactory?.also { addConverterFactory(it) }
    }.build().create(service)
}
