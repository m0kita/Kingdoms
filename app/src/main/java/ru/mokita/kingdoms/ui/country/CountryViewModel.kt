package ru.mokita.kingdoms.ui.country

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.mokita.domain.model.Country

class CountryViewModel(private val args: CountryFragmentArgs): ViewModel() {
    private var _country = MutableStateFlow(Country())
    val country =_country.asStateFlow()

    init {
        _country.value = Country(args.name, args.flag, args.capital, args.region, args.currency, args.timezones)
    }
}