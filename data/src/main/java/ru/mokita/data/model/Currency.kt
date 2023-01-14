package ru.mokita.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Currency(
    @Json(name = "code")val code: String = "",
    @Json(name = "name")val name: String = "",
    @Json(name = "symbol") val symbol: String = "",
)