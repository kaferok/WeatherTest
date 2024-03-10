package com.veko.weatherexample.fragment.dailyWeather.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.veko.weatherexample.databinding.ItemDailyWeatherBinding

class DailyAdapter : ListAdapter<DailyItem, DailyViewHolder>(DailyDiffUtils()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyViewHolder =
        DailyViewHolder(
            ItemDailyWeatherBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: DailyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}