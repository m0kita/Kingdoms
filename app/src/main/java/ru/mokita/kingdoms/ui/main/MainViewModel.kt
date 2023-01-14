package ru.mokita.kingdoms.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.mokita.data.repository.CountryRepositoryImpl
import ru.mokita.domain.model.Country
import ru.mokita.domain.usecase.LoadCountriesUseCase

class MainViewModel: ViewModel() {
    private val _countries = MutableStateFlow(listOf<Country>())
    val countries = _countries.asStateFlow()
    private val loadCountriesUseCase = LoadCountriesUseCase(CountryRepositoryImpl())

    init {
        viewModelScope.launch {
            updateCountries()
        }
    }

    suspend fun updateCountries() {
        if (_countries.value.isEmpty()) {
                _countries.value = loadCountriesUseCase.execute()
        } else {
            _countries.value = listOf<Country>()
            _countries.value = loadCountriesUseCase.execute()
        }
    }
}