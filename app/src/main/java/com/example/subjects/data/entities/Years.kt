package com.example.subjects.data.entities

import android.annotation.SuppressLint
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.subjects.R

@Entity
data class Years(

    @PrimaryKey(autoGenerate = true)
    var yearId: Int,

    @SuppressLint("NonConstantResourceId") @ColumnInfo(defaultValue = "${R.string.first_year}")
    val yearName: Int?,

    @SuppressLint("NonConstantResourceId") @ColumnInfo(defaultValue = "${R.drawable.sumphor}")
    val icon: Int?
)