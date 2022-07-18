package com.example.subjects.data.entities

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import kotlinx.parcelize.Parcelize


data class SubjectWithQualification(
    @Embedded
    val subject: Subject,
    @Relation(
        parentColumn ="subjectNameId",
        entityColumn = "subjectNameId"
    )
    val qualification: Qualification
)
