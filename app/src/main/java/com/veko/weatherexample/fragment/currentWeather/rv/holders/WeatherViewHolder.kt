package com.veko.weatherexample.fragment.currentWeather.rv.holders

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.veko.weatherexample.databinding.ItemCurrentWeatherBinding
import com.veko.weatherexample.fragment.currentWeather.rv.WeatherItems

class WeatherViewHolder(
    private val binding: ItemCurrentWeatherBinding,
    private val onArrowClick: (item: WeatherItems.Weather) -> Unit
) : ViewHolder(binding.root) {

    fun bind(item: WeatherItems.Weather) {
        with(binding) {
            tvCity.text = item.city
            tvFeelsLike.text = item.feelsLike
            tvHumidity.text = item.humidity
            tvWindSpeed.text = item.windSpeed
            tvPressure.text = item.pressure
            tvVisibility.text = item.visibility
            tvTemperature.text = item.temperature
            tvWeatherMain.text = item.main

            val rotationValue = if (item.isOpened) 0F else 180F
            imgArrow.animate().rotation(rotationValue)
            detailsContainer.isVisible = item.isOpened
            imgArrow.setOnClickListener {
                onArrowClick(item)
            }

            Glide.with(root).load(item.icon).into(imgWeather)
        }
    }

    fun bindPayload(item: WeatherItems.Weather) {
        with(binding) {
            val rotationValue = if (item.isOpened) 0F else 180F
            imgArrow.animate().rotation(rotationValue)
            detailsContainer.isVisible = item.isOpened
            imgArrow.setOnClickListener {
                onArrowClick(item)
            }
        }
    }
}