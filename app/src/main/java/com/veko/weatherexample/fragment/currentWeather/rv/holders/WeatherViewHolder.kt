package com.veko.weatherexample.fragment.currentWeather.rv.holders

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.veko.weatherexample.databinding.ItemCurrentWeatherBinding
import com.veko.weatherexample.fragment.currentWeather.rv.WeatherItems

class WeatherViewHolder(
    private val binding: ItemCurrentWeatherBinding,
    private val onArrowClick: (item: WeatherItems.Weather) -> Unit,
    private val onItemClick: (item: WeatherItems.Weather) -> Unit,
) : ViewHolder(binding.root) {

    fun bind(item: WeatherItems.Weather) {
        with(binding) {
            tvCity.text = item.city
            tvDate.text = item.date
            tvFeelsLike.text = item.feelsLike
            tvHumidity.text = item.humidity
            tvWindSpeed.text = item.windSpeed
            tvPressure.text = item.pressure
            tvVisibility.text = item.visibility
            tvTemperature.text = item.temperature
            tvWeatherMain.text = item.main

            val rotationValue = if (item.isOpened) 180F else 0F
            imgArrow.animate().rotation(rotationValue)
            detailsContainer.isVisible = item.isOpened
            imgArrow.setOnClickListener {
                onArrowClick(item)
            }

            itemView.setOnClickListener {
                onItemClick(item)
            }

            Glide.with(root).load(item.icon).into(imgWeather)
        }
    }

    fun bindPayload(item: WeatherItems.Weather) {
        with(binding) {
            val rotationValue = if (item.isOpened) 180F else 0F
            imgArrow.animate().rotation(rotationValue)
            detailsContainer.isVisible = item.isOpened
            imgArrow.setOnClickListener {
                onArrowClick(item)
            }
        }
    }
}