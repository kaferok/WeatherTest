package com.veko.weatherexample.fragment.weather.rv.holders

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.veko.weatherexample.databinding.ItemAddCityBinding
import com.veko.weatherexample.fragment.weather.rv.WeatherItems

class AddCityViewHolder(
    private val binding: ItemAddCityBinding,
    private val onAddClick: () -> Unit
) : ViewHolder(binding.root) {

    fun bind(item: WeatherItems.AddButton) {
        binding.imgAdd.setOnClickListener { onAddClick() }
    }
}