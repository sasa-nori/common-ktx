package net.newstyleservice.common_ktx.extension

import android.util.Log
import com.squareup.moshi.Moshi

fun Any.toJsonString(isLog: Boolean = false): String? {
    return try {
        val moshi = Moshi.Builder().build()
        moshi.adapter(Any::class.java).toJson(this)
    } catch (e: Exception) {
        if (isLog) {
            Log.e("parse error", "Have you forgotten the annotation on Class?\n" +
                    "Moshi code gen import and adapter generation settings\n" +
                    "https://github.com/square/moshi#codegen")
        }
        ""
    }
}
