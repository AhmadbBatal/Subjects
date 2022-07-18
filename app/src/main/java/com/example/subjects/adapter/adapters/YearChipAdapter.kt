package com.example.subjects.adapter.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.subjects.R
import com.example.subjects.adapter.comparators.YearChipComparators
import com.example.subjects.adapter.viewholders.YearsChipViewHolder
import com.example.subjects.data.entities.Years
import com.example.subjects.databinding.ItemYearsChipsBinding

class YearChipAdapter(
    private val listener: OnChipClick
) : ListAdapter<Years, YearsChipViewHolder>(YearChipComparators()) {

    private var selectedItem = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YearsChipViewHolder {
        val binding =
            ItemYearsChipsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return YearsChipViewHolder(binding)
    }

    @SuppressLint("ResourceAsColor", "NewApi")
    override fun onBindViewHolder(holder: YearsChipViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.binding.apply {

                txtYears.text = currentItem.yearName?.let {
                    holder.itemView.context.getString(it)
                }

                currentItem.icon?.let {
                    imgIconShip.setImageResource(it)
                }

                mainCardChipCategory.setCardBackgroundColor(
                    holder.itemView.context
                        .getColor(R.color.mid_blue)
                )

                mainCardChipCategory.cardElevation= 0F

                if(selectedItem == position)
                {
                    mainCardChipCategory.setCardBackgroundColor(
                        holder.itemView.context
                            .getColor(R.color.orange)
                    )
                    mainCardChipCategory.cardElevation = 20F
                }

                root.setOnClickListener {
                    val chip = getItem(position)
                    if(chip != null)
                    {
                        listener.onChipClickListener(chip)
                    }
                    selectedItems(position)
                }

            }
        }
    }

    private fun selectedItems(pos: Int) {
        val previewItem: Int = selectedItem
        selectedItem = pos
        notifyItemChanged(previewItem)
        notifyItemChanged(pos)
    }

    interface OnChipClick {
        fun onChipClickListener(chip: Years)
    }
}