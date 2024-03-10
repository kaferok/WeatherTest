package com.veko.weatherexample.fragment.currentWeather.rv

import androidx.recyclerview.widget.DiffUtil

class WeatherDiffUtils : DiffUtil.ItemCallback<WeatherItems>() {

    override fun areItemsTheSame(oldItem: WeatherItems, newItem: WeatherItems): Boolean =
        when {
            oldItem is WeatherItems.Weather && newItem is WeatherItems.Weather -> oldItem.city == newItem.city
            else -> oldItem == newItem
        }

    override fun areContentsTheSame(oldItem: WeatherItems, newItem: WeatherItems): Boolean =
        oldItem == newItem

    override fun getChangePayload(oldItem: WeatherItems, newItem: WeatherItems): Any? = when {
        oldItem is WeatherItems.Weather && newItem is WeatherItems.Weather -> newItem
        else -> null
    }

}