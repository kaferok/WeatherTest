package com.veko.weatherexample.fragment.currentWeather

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.veko.weatherexample.R
import com.veko.weatherexample.databinding.FragmentWeatherBinding
import com.veko.weatherexample.fragment.currentWeather.rv.WeatherAdapter
import com.veko.weatherexample.fragment.currentWeather.rv.WeatherItems
import com.veko.weatherexample.utils.navigateTo
import com.veko.weatherexample.utils.rv.AddCityDecoration
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherFragment : Fragment() {

    private val binding by viewBinding<FragmentWeatherBinding>()

    private val viewModel by viewModel<WeatherViewModel>()

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        WeatherAdapter(
            onAddClick = viewModel::onAddClicked,
            onArrowClick = viewModel::onArrowClicked
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeViewModel()
    }

    private fun initView() {
        with(binding) {
            rvWeather.adapter = adapter

            val bottomMargin = resources.getDimensionPixelSize(R.dimen.add_button_margin)
            rvWeather.addItemDecoration(AddCityDecoration(bottomMargin))
        }
    }

    private fun observeViewModel() {
        with(viewModel) {
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.RESUMED) {
                    viewStateFlow.collectLatest { state ->
                        updateRv(state.weather)
                    }
                }
            }

            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.RESUMED) {
                    actionFlow.collectLatest { action ->
                        when (action) {
                            WeatherViewAction.AddNew -> navigateTo(WeatherFragmentDirections.actionToAddCity())
                        }
                    }
                }
            }
        }
    }

    private fun updateRv(items: List<WeatherItems>) {
        adapter.submitList(items)
    }
}