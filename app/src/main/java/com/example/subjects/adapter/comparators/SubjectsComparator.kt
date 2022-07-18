package com.example.subjects.adapter.comparators

import androidx.recyclerview.widget.DiffUtil
import com.example.subjects.data.entities.SubjectWithQualification

class SubjectsComparator: DiffUtil.ItemCallback<SubjectWithQualification>()
{
    override fun areItemsTheSame(oldItem: SubjectWithQualification, newItem: SubjectWithQualification) =
        oldItem.subject.subjectNameId == newItem.subject.subjectNameId

    override fun areContentsTheSame(oldItem: SubjectWithQualification, newItem: SubjectWithQualification) =
        oldItem == newItem
}