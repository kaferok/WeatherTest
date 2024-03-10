package com.veko.weatherexample.fragment.listCity

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.veko.weatherexample.databinding.FragmentListCityBinding
import com.veko.weatherexample.fragment.listCity.rv.ListCityAdapter
import kotlinx.coroutines.launch

class ListCityFragment : Fragment() {

    private val binding by viewBinding<FragmentListCityBinding>()

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        ListCityAdapter(
            onAddClick = {},
            onArrowClick = {}
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {

            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {

            }
        }
    }
}