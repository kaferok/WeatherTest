package com.veko.weatherexample.fragment.addCity

import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.veko.weatherexample.databinding.FragmentAddCityBinding
import kotlinx.coroutines.launch

class AddCityDialogFragment : DialogFragment() {

    private val binding by viewBinding<FragmentAddCityBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindListeners()
        observeViewModel()
    }

    private fun bindListeners() {
        with(binding) {
            etCity.doAfterTextChanged {

            }

            btnAdd.setOnClickListener {

            }

            btnCancel.setOnClickListener {
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {

            }
        }
    }
}