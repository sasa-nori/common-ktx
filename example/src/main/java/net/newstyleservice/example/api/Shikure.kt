package net.newstyleservice.example.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Shikure(
    val Id: Int,
    val content: String,
    val day: String,
    val hour: Int,
    val version: Int
)
