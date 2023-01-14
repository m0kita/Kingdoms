package ru.mokita.domain.repository

import ru.mokita.domain.model.Country

interface CountryRepository {
    suspend fun getCountries():  List<Country>
}