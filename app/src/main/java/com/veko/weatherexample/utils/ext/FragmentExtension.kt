package com.veko.weatherexample.utils.ext

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import com.veko.weatherexample.activity.ActivityBinder

fun Fragment.navigateTo(direction: NavDirections) {
    val activityBinder = requireActivity() as ActivityBinder
    activityBinder.navigateTo(direction)
}

fun Fragment.navigateUp() {
    val activityBinder = requireActivity() as ActivityBinder
    activityBinder.navigateUp()
}

fun Fragment.toast(message: String) =
    Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()