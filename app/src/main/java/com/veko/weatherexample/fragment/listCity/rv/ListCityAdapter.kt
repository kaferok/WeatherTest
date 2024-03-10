package com.veko.weatherexample.fragment.listCity.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.veko.weatherexample.databinding.ItemAddCityBinding
import com.veko.weatherexample.databinding.ItemCityBinding
import com.veko.weatherexample.fragment.listCity.rv.holders.AddCityViewHolder
import com.veko.weatherexample.fragment.listCity.rv.holders.CityViewHolder

class ListCityAdapter(
    private val onArrowClick: (ListCityItems.Weather) -> Unit,
    private val onAddClick: () -> Unit
) : ListAdapter<ListCityItems, RecyclerView.ViewHolder>(ListCityDiffUtils()) {

    companion object {
        private const val CITY = 0
        private const val ADD_CITY = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            CITY -> CityViewHolder(
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
            is ListCityItems.Weather -> (holder as? CityViewHolder)?.bind(item)
            is ListCityItems.AddButton -> (holder as? AddCityViewHolder)?.bind(item)
        }
    }

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is ListCityItems.Weather -> CITY
        is ListCityItems.AddButton -> ADD_CITY
    }
}