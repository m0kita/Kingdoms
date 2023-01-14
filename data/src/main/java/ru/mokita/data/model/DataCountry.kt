package ru.mokita.data.model

import com.squareup.moshi.Json


data class DataCountry(
    @Json(name = "name")val name: String,
    @Json(name = "flags")val flags: Flags,
    @Json(name = "region")val region: String,
    @Json(name = "capital")val capital: String?,
    @Json(name = "timezones")val timezones: List<String>,
    @Json(name = "currencies")val currencies: List<Currency>?,
)