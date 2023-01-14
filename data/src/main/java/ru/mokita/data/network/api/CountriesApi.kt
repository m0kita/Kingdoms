package ru.mokita.data.network.api

import retrofit2.http.GET
import ru.mokita.data.model.DataCountry
import ru.mokita.domain.model.Country

interface CountriesApi {

    @GET("all")
    suspend fun getCountries(): List<DataCountry>
}