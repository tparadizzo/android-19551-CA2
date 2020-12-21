package com.example.tabitaparadizzomfusion.models
/*
Tabita - 19551
Marcelle - 19534
*/

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meal_steps")
data class MealStep(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "meal_id") var mealId: Long,
    @ColumnInfo(name = "title") var title: String
)