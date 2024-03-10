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
import com.veko.weatherexample.utils.navigateUp
import com.veko.weatherexample.utils.toast
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddCityDialogFragment : DialogFragment() {

    private val binding by viewBinding<FragmentAddCityBinding>()

    private val viewModel by viewModel<AddCityViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindListeners()
        observeViewModel()
    }

    private fun bindListeners() {
        with(binding) {
            tilCity.editText?.doAfterTextChanged {
                viewModel.onCityChanged(it.toString())
            }

            btnAdd.setOnClickListener {
                viewModel.onAddClicked()
            }

            btnCancel.setOnClickListener {
                navigateUp()
            }
        }
    }

    private fun observeViewModel() {
        with(viewModel) {
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.RESUMED) {
                    actionFlow.collectLatest { action ->
                        when (action) {
                            AddCityViewAction.Added -> navigateUp()
                            is AddCityViewAction.Error -> toast(action.message)
                        }
                    }
                }
            }
        }
    }
}