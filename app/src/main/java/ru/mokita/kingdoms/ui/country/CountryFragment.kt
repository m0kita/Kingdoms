package ru.mokita.kingdoms.ui.country

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import kotlinx.coroutines.flow.collect
import ru.mokita.domain.model.Country
import ru.mokita.kingdoms.R
import ru.mokita.kingdoms.databinding.FragmentCountryBinding
import ru.mokita.kingdoms.databinding.FragmentHomeBinding

class CountryFragment : Fragment() {
    private var _binding: FragmentCountryBinding? = null
    private val binding get() = _binding!!
    private val args: CountryFragmentArgs by navArgs()
    private val countryViewModel: CountryViewModel by lazy { CountryViewModel(args)}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCountryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenStarted {
            countryViewModel.country.collect{
                setupView(it)
            }
        }
    }

    private fun setupView(country: Country) {
        Glide.with(requireContext()).load(country.flag).into(binding.ivCountryFlag)
        binding.tvName.text = getString(R.string.name, country.name)
        if (country.capital.isBlank()) {
            binding.tvCapital.text = getString(R.string.capital, "---")
        } else {
            binding.tvCapital.text = getString(R.string.capital, country.capital)
        }
        binding.tvRegion.text = getString(R.string.region, country.region)
        if (country.currency.isBlank()) {
            binding.tvCurrency.text = getString(R.string.currency, "---")
        } else {
            binding.tvCurrency.text = getString(R.string.currency, country.currency)
        }
        binding.tvTimezones.text = getString(R.string.timezones, country.timezones)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}