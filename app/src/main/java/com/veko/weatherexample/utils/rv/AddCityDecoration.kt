package com.veko.weatherexample.utils.rv

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class AddCityDecoration(private val bottom: Int) : ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val size = parent.adapter?.itemCount
        if (position == size) {
            outRect.bottom = bottom
        }
    }
}