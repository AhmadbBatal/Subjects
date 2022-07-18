package com.example.subjects.adapter.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.subjects.R
import com.example.subjects.adapter.comparators.SubIconsComparators
import com.example.subjects.adapter.viewholders.SubIconsViewHolder
import com.example.subjects.data.models.SubIcons
import com.example.subjects.databinding.ItemIconsBinding
import com.example.subjects.util.subIcons

class SubIconsAdapter(
    private val onIconClick: OnClickIcon
) :
    ListAdapter<SubIcons, SubIconsViewHolder>(SubIconsComparators()) {

    private var selectedItem = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubIconsViewHolder {
        val binding = ItemIconsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SubIconsViewHolder(binding)
    }

    @SuppressLint("NewApi")
    override fun onBindViewHolder(holder: SubIconsViewHolder, position: Int) {
        val currentItem = getItem(position)

        holder.binding.apply {

            currentItem.icon?.let {imgIcon.setImageResource(it) }

            itemIcons.setCardBackgroundColor(
                holder.itemView.context.getColor(R.color.dark_blue)
            )
            itemIcons.cardElevation = 0f

            if(selectedItem == position)
            {
                itemIcons.setCardBackgroundColor(holder.itemView.context.getColor(R.color.mid_blue))
                itemIcons.cardElevation = 12f
            }

            itemIcons.setOnClickListener {
                val icon = getItem(position)
                if(icon != null)
                {
                    onIconClick.onClickIcons(icon)
                }
                selectedItems(position)
            }
        }
    }

    interface OnClickIcon{
        fun onClickIcons(subIcon: SubIcons)
    }

    private fun selectedItems(pos: Int) {
        val previewItem: Int = selectedItem
        selectedItem = pos
        notifyItemChanged(previewItem)
        notifyItemChanged(pos)
    }
}