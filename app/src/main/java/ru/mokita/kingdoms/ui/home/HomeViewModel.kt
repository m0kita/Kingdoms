package ru.mokita.kingdoms.ui.home

import android.util.Log
import android.view.View
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.bumptech.glide.Glide.init
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.mokita.data.repository.CountryRepositoryImpl
import ru.mokita.domain.model.Country
import ru.mokita.domain.usecase.LoadCountriesUseCase
import ru.mokita.kingdoms.ui.home.adapter.CountriesAdapter

class HomeViewModel : ViewModel() {
    var position: Int = 0
}