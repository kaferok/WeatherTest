package com.veko.weatherexample.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.veko.weatherexample.R
import com.veko.weatherexample.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherActivity : AppCompatActivity(), ActivityBinder {

    private val viewModel by viewModel<WeatherActivityViewModel>()

    private val binding by viewBinding<ActivityMainBinding>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        observeViewModel()
    }

    override fun navigateTo(direction: NavDirections) {
        findNavController(R.id.mainContainer).navigate(direction)
    }

    override fun navigateUp() {
        findNavController(R.id.mainContainer).navigateUp()
    }

    private fun observeViewModel() {
        with(viewModel) {
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.RESUMED) {
                    actionFlow.collectLatest { action ->
                        when (action) {
                            WeatherActivityViewAction.AvailableConnection -> binding.cardNoConnection.isVisible =
                                false

                            WeatherActivityViewAction.LostConnection -> binding.cardNoConnection.isVisible =
                                true
                        }
                    }
                }
            }
        }
    }
}