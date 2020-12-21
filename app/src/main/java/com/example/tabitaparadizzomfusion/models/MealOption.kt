package com.example.tabitaparadizzomfusion.models
/*
Tabita - 19551
Marcelle - 19534
*/

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meal_options")
data class MealOption (
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "meal_step_id") val mealStepId: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "price") val price: Double
)