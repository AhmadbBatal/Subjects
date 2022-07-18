package com.example.subjects.data.entities

import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.subjects.R
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Subject(
    @PrimaryKey(autoGenerate = false)
    val subjectNameId: String,
    val yearId: Int,
    val number:Int,
    val subjectName: String?,
    val qualification: String?,
    val subjectColor: String?,
    val teacherName: String?,  // Can Change
    val practicalMarks: String?, // Can Change
    val finalMark: String?, // Can Change
    @SuppressLint("NonConstantResourceId") @ColumnInfo(defaultValue = "${R.drawable.mind}")
    val subjectImage: Int?,  // Can Change
    val collage: String?,
    val subHours: String?,
    val subYears: String?,
) : Parcelable









