package ru.mokita.data.repository

import ru.mokita.data.model.DataCountry
import ru.mokita.data.network.ApiClient
import ru.mokita.domain.model.Country
import ru.mokita.domain.repository.CountryRepository

class CountryRepositoryImpl : CountryRepository {
    private val client = ApiClient.client

    override suspend fun getCountries(): List<Country> {
        return client.getCountries().map { fromDataToDomain(it) }
    }

    private fun fromDataToDomain(country: DataCountry): Country {
        var currency = ""
        if (country.currencies != null) {
            currency = country.currencies[0].name
        }
        var timezones = country.timezones.joinToString(", ")
        return Country(name = country.name, flag = country.flags.png, region = country.region, capital = country.capital ?: "", currency = currency, timezones = timezones)
    }
}