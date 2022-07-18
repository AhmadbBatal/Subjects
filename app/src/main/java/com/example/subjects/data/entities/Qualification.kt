package com.example.subjects.data.entities

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.subjects.util.enums.SubjectStatue
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "qualification_table")
data class Qualification(
    @PrimaryKey(autoGenerate = false) @NonNull var subjectNameId: String = "",
    val qualificationNameId: String?,
    val nextSubjectId: String?,
    val nextSubjectTow: String?,
    val nextSubjectThree: String?,
    val isHasQualification: Boolean,
    val subjectStatue: SubjectStatue?,
): Parcelable
