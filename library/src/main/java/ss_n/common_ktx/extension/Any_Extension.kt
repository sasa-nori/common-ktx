package ss_n.common_ktx.extension

import android.os.Bundle
import android.util.Log
import androidx.core.os.bundleOf
import com.squareup.moshi.Moshi

/**
 * Object to Json String
 *  Must installed Moshi Code gen!!
 *
 * @param isLog true is print error logs
 * @return parsed object
 */
fun Any.toJsonString(isLog: Boolean = false): String? {
    return try {
        val moshi = Moshi.Builder().build()
        moshi.adapter(Any::class.java).toJson(this)
    } catch (e: Exception) {
        if (isLog) {
            Log.e(
                "parsed error", "Have you forgotten the annotation on Class?\n" +
                    "Moshi code gen import and adapter generation settings\n" +
                    "https://github.com/square/moshi#codegen"
            )
        }
        null
    }
}

/**
 * Create Bundle
 *
 * @param key key
 * @return [Bundle]
 */
fun Any.toBundleValue(key: String): Bundle = bundleOf(key to this)

/**
 * Create MutableMap
 *
 * @param key key
 * @return [MutableMap]
 */
fun Any.toMutableMapValue(key: String): MutableMap<String, Any> = mutableMapOf(key to this)

/**
 * Create Map
 *
 * @param key key
 * @return [Map]
 */
fun Any.toMapValue(key: String): Map<String, Any> = mapOf(key to this)
