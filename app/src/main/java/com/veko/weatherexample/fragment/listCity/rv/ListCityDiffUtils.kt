package com.veko.weatherexample.fragment.listCity.rv

import androidx.recyclerview.widget.DiffUtil

class ListCityDiffUtils : DiffUtil.ItemCallback<ListCityItems>() {

    override fun areItemsTheSame(oldItem: ListCityItems, newItem: ListCityItems): Boolean =
        when {
            oldItem is ListCityItems.Weather && newItem is ListCityItems.Weather -> oldItem.city == newItem.city
            else -> oldItem == newItem
        }

    override fun areContentsTheSame(oldItem: ListCityItems, newItem: ListCityItems): Boolean =
        oldItem == newItem

    override fun getChangePayload(oldItem: ListCityItems, newItem: ListCityItems): Any? {
        return super.getChangePayload(oldItem, newItem)
    }
}