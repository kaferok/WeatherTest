package com.veko.weatherexample.fragment.dailyWeather

import android.content.Context
import com.veko.weatherexample.utils.ext.navigateUp
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.veko.weatherexample.R
import com.veko.weatherexample.databinding.FragmentWeatherBinding
import com.veko.weatherexample.fragment.dailyWeather.rv.DailyAdapter
import com.veko.weatherexample.fragment.dailyWeather.rv.DailyItem
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DailyFragment : Fragment(R.layout.fragment_weather) {

    private val navArgs by navArgs<DailyFragmentArgs>()

    private val binding by viewBinding<FragmentWeatherBinding>()

    private val viewModel by viewModel<DailyViewModel>(parameters = {
        parametersOf(navArgs.city)
    })

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        DailyAdapter()
    }

    private val backPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            navigateUp()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireActivity().onBackPressedDispatcher.addCallback(backPressedCallback)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeViewModel()
    }

    private fun initView() {
        with(binding) {
            progress.isVisible = false
            tvTitle.text = getString(R.string.daily_forecast)
            rvWeather.layoutManager = GridLayoutManager(requireContext(), 3)
            rvWeather.adapter = adapter
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
        }
    }

    private fun updateRv(items: List<DailyItem>) {
        adapter.submitList(items)
    }
}