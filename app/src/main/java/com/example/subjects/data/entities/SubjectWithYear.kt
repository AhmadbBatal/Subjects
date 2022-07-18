package com.example.subjects.data.entities

import androidx.room.Embedded
import androidx.room.Relation


// Notes
// Should parentColumn And entityColumn Has Same Name
data class SubjectWithYear (
    @Embedded
    val year : Years,
    @Relation(
        parentColumn = "yearId",
        entityColumn = "yearId"
    )
    val subject : List<Subject>
)