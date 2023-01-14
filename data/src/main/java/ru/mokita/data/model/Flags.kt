package ru.mokita.data.model

import com.squareup.moshi.Json

data class Flags(
    @Json(name = "png") val png: String,
    @Json(name = "svg") val svg: String,
)
