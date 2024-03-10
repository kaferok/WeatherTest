package com.veko.weatherexample.fragment.dailyWeather.rv

import androidx.recyclerview.widget.DiffUtil.ItemCallback

class DailyDiffUtils : ItemCallback<DailyItem>() {
    override fun areItemsTheSame(oldItem: DailyItem, newItem: DailyItem): Boolean = true

    override fun areContentsTheSame(oldItem: DailyItem, newItem: DailyItem): Boolean = true
}