package com.veko.weatherexample.fragment.addCity

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.veko.weatherexample.R
import com.veko.weatherexample.databinding.FragmentAddCityBinding
import com.veko.weatherexample.utils.ext.navigateUp
import com.veko.weatherexample.utils.ext.toast
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddCityDialogFragment : DialogFragment(R.layout.fragment_add_city) {

    private val binding by viewBinding<FragmentAddCityBinding>()

    private val viewModel by viewModel<AddCityViewModel>()
    override fun onResume() {
        val sideMargin = resources.getDimensionPixelSize(R.dimen.dp_36)
        val inset = InsetDrawable(
            ColorDrawable(Color.TRANSPARENT),
            sideMargin,
            0,
            sideMargin,
            0
        )
        val params = dialog?.window?.attributes
        params?.height = WindowManager.LayoutParams.WRAP_CONTENT
        params?.width = WindowManager.LayoutParams.MATCH_PARENT
        dialog?.window?.attributes = params
        dialog?.window?.setBackgroundDrawable(inset)
        super.onResume()
    }


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

            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.RESUMED) {
                    viewStateFlow.collectLatest { state ->
                        binding.progress.isVisible = state.loading
                        binding.tilCity.error = state.textError
                    }
                }
            }
        }
    }
}