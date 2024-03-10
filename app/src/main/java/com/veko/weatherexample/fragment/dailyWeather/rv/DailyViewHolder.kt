package com.veko.weatherexample.fragment.dailyWeather.rv

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.veko.weatherexample.databinding.ItemDailyWeatherBinding

class DailyViewHolder(
    private val binding: ItemDailyWeatherBinding
) : ViewHolder(binding.root) {

    fun bind(item: DailyItem) {
        with(binding) {
            tvTemperature.text = item.temperature
            tvDate.text = item.date
            tvMain.text = item.main

            Glide.with(root).load(item.icon).into(imgWeather)
        }
    }
}