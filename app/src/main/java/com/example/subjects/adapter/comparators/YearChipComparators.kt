package com.example.subjects.adapter.comparators

import androidx.recyclerview.widget.DiffUtil
import com.example.subjects.data.entities.Years

class YearChipComparators : DiffUtil.ItemCallback<Years>() {

    override fun areItemsTheSame(oldItem: Years, newItem: Years) =
        oldItem.yearId == newItem.yearId

    override fun areContentsTheSame(oldItem: Years, newItem: Years) =
        oldItem == newItem
}