package com.veko.weatherexample.fragment.weather.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.veko.weatherexample.databinding.ItemAddCityBinding
import com.veko.weatherexample.databinding.ItemCityBinding
import com.veko.weatherexample.fragment.weather.rv.holders.AddCityViewHolder
import com.veko.weatherexample.fragment.weather.rv.holders.WeatherViewHolder

class WeatherAdapter(
    private val onArrowClick: (WeatherItems.Weather) -> Unit,
    private val onAddClick: () -> Unit
) : ListAdapter<WeatherItems, RecyclerView.ViewHolder>(WeatherDiffUtils()) {

    companion object {
        private const val CITY = 0
        private const val ADD_CITY = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            CITY -> WeatherViewHolder(
                binding = ItemCityBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                onArrowClick = onArrowClick
            )

            ADD_CITY -> AddCityViewHolder(
                binding = ItemAddCityBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                onAddClick = onAddClick
            )

            else -> error("Can't create ViewHolder for unknown ViewType")
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = getItem(position)) {
            is WeatherItems.Weather -> (holder as? WeatherViewHolder)?.bind(item)
            is WeatherItems.AddButton -> (holder as? AddCityViewHolder)?.bind(item)
        }
    }

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is WeatherItems.Weather -> CITY
        is WeatherItems.AddButton -> ADD_CITY
    }
}