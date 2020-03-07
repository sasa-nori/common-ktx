package net.newstyleservice.common_ktx.extension

import com.squareup.moshi.Moshi

fun Any.toJsonString(): String? {
    val moshi = Moshi.Builder().build()
    return moshi.adapter(Any::class.java).toJson(this)
}