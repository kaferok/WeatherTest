package com.veko.weatherexample.fragment.currentWeather.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.veko.domain.model.CurrentWeather
import com.veko.weatherexample.databinding.ItemAddCityBinding
import com.veko.weatherexample.databinding.ItemCurrentWeatherBinding
import com.veko.weatherexample.fragment.currentWeather.rv.holders.AddCityViewHolder
import com.veko.weatherexample.fragment.currentWeather.rv.holders.WeatherViewHolder

class WeatherAdapter(
    private val onArrowClick: (WeatherItems.Weather) -> Unit,
    private val onAddClick: () -> Unit,
    private val onItemClick: (WeatherItems.Weather) -> Unit
) : ListAdapter<WeatherItems, RecyclerView.ViewHolder>(WeatherDiffUtils()) {

    companion object {
        private const val CITY = 0
        private const val ADD_CITY = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            CITY -> WeatherViewHolder(
                binding = ItemCurrentWeatherBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                onArrowClick = onArrowClick,
                onItemClick = onItemClick
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

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        payloads.firstOrNull()?.let { payload ->
            when (payload) {
                is WeatherItems.Weather -> (holder as WeatherViewHolder).bindPayload(payload)
                else -> super.onBindViewHolder(holder, position, payloads)
            }
        }
        super.onBindViewHolder(holder, position, payloads)
    }

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is WeatherItems.Weather -> CITY
        is WeatherItems.AddButton -> ADD_CITY
    }
}