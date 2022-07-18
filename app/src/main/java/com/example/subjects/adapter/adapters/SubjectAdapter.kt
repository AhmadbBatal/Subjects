package com.example.subjects.adapter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.subjects.adapter.comparators.SubjectsComparator
import com.example.subjects.adapter.viewholders.SubjectViewHolder
import com.example.subjects.data.entities.SubjectWithQualification
import com.example.subjects.databinding.ItemHomeSubjectsListBinding

class SubjectAdapter(
    private val onSubjectClick: (Boolean) -> Unit,
    private val onBtnEditSubjectClick: (SubjectWithQualification) -> Unit,
    private val onBtnViewSubjectClick: (SubjectWithQualification) -> Unit

) : ListAdapter<SubjectWithQualification, SubjectViewHolder>(SubjectsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val binding = ItemHomeSubjectsListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SubjectViewHolder(
            binding,
            onSubjectItemClick = {
                onSubjectClick(it)
            },
            onBtnEditSubjectClick = { position ->
                val subject = getItem(position)
                if (subject != null) {
                    onBtnEditSubjectClick(subject)
                }
            },
            onBtnViewSubjectClick = { position ->
                val subject = getItem(position)
                if (subject != null) {
                    onBtnViewSubjectClick(subject)
                }
            }
        )
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }
}