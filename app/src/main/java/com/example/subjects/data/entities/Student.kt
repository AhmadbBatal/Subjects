package com.example.subjects.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Student (

    @PrimaryKey(autoGenerate = true)
    val studentId : Int?,

    @ColumnInfo(defaultValue = "0")
    val studentYear: Int?,

    @ColumnInfo(defaultValue = "0")
    val studentMark :Int?,

    @ColumnInfo(defaultValue = "0")
    val ElapsedTime :Int?,

    @ColumnInfo(defaultValue = "0")
    val remainingTime: Int?,
)