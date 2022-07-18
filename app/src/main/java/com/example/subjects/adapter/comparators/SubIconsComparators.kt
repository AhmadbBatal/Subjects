package com.example.subjects.adapter.comparators

import androidx.recyclerview.widget.DiffUtil
import com.example.subjects.data.models.SubIcons

class SubIconsComparators: DiffUtil.ItemCallback<SubIcons>() {
    override fun areItemsTheSame(oldItem: SubIcons, newItem: SubIcons) =
        oldItem.icon == newItem.icon

    override fun areContentsTheSame(oldItem: SubIcons, newItem: SubIcons) =
        oldItem == newItem
}