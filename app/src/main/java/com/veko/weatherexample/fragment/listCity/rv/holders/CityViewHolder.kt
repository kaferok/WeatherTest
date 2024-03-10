package com.veko.weatherexample.fragment.listCity.rv.holders

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.veko.weatherexample.databinding.ItemCityBinding
import com.veko.weatherexample.fragment.listCity.rv.ListCityItems

class CityViewHolder(
    private val binding: ItemCityBinding,
    private val onArrowClick: (item: ListCityItems.Weather) -> Unit
) : ViewHolder(binding.root) {

    fun bind(item: ListCityItems.Weather) {

    }
}