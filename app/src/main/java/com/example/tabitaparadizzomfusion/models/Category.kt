package com.example.tabitaparadizzomfusion.models
/*
Tabita - 19551
Marcelle - 19534
*/

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class Category(
    @PrimaryKey(autoGenerate = true) val id: Long?,
    @ColumnInfo(name = "name") val name: String?
)