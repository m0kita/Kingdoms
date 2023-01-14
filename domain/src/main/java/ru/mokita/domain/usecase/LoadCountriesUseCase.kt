package ru.mokita.domain.usecase

import ru.mokita.domain.model.Country
import ru.mokita.domain.repository.CountryRepository

class LoadCountriesUseCase(private val repository: CountryRepository) {
    suspend fun execute(): List<Country> {
        return repository.getCountries()
    }
}