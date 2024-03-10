package com.veko.weatherexample.fragment.listCity.rv.holders

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.veko.weatherexample.databinding.ItemAddCityBinding
import com.veko.weatherexample.fragment.listCity.rv.ListCityItems

class AddCityViewHolder(
    private val binding: ItemAddCityBinding,
    private val onAddClick: () -> Unit
) : ViewHolder(binding.root) {

    fun bind(item: ListCityItems.AddButton) {
        binding.imgAdd.setOnClickListener { onAddClick() }
    }
}