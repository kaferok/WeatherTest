package com.veko.weatherexample.fragment.weather.rv.holders

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.veko.weatherexample.databinding.ItemCityBinding
import com.veko.weatherexample.fragment.weather.rv.WeatherItems

class WeatherViewHolder(
    private val binding: ItemCityBinding,
    private val onArrowClick: (item: WeatherItems.Weather) -> Unit
) : ViewHolder(binding.root) {

    fun bind(item: WeatherItems.Weather) {
        with(binding) {
            detailsContainer.isVisible = item.isOpened
            tvCity.text = item.city
            tvFeelsLike.text = item.feelsLike
            tvHumidity.text = item.humidity
            tvWindSpeed.text = item.windSpeed
            tvPressure.text = item.pressure
            tvVisibility.text = item.visibility
            tvTemperature.text = item.temperature
            tvWeatherMain.text = item.main
            imgArrow.setOnClickListener {
                val rotationValue = if (item.isOpened) 0F else 180F
                onArrowClick(item)
                imgArrow.animate().rotation(rotationValue)
            }

            Glide.with(root).load(item.icon).into(imgWeather)
        }
    }
}