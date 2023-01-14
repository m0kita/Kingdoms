package ru.mokita.kingdoms.ui.home

import android.os.Bundle
import android.provider.SyncStateContract.Helpers.update
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.mokita.domain.model.Country
import ru.mokita.kingdoms.R
import ru.mokita.kingdoms.databinding.FragmentCountryBinding
import ru.mokita.kingdoms.databinding.FragmentHomeBinding
import ru.mokita.kingdoms.ui.country.CountryFragmentDirections
import ru.mokita.kingdoms.ui.home.adapter.CountriesAdapter
import ru.mokita.kingdoms.ui.main.MainViewModel

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel: HomeViewModel by viewModels()
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenStarted {
            mainViewModel.countries.collect {
                setupRecycler()
            }
        }
        binding.fabUpdate.setOnClickListener {
            lifecycleScope.launch {
                mainViewModel.updateCountries()
            }
        }
    }

    private fun setupRecycler() {
        val layoutManager = LinearLayoutManager(requireContext())
        val adapter = CountriesAdapter(
            mainViewModel.countries.value,
            requireContext()
        ) {country, position ->
            val action = HomeFragmentDirections.actionHomeFragmentToCountryFragment(country.name, country.region, country.flag, country.capital, country.currency, country.timezones)
            homeViewModel.position = position
            findNavController().navigate(action)
        }
        binding.rvCountries.adapter = adapter
        binding.rvCountries.layoutManager = layoutManager
        binding.rvCountries.scrollToPosition(homeViewModel.position)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}