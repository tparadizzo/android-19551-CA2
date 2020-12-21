package com.example.tabitaparadizzomfusion.models
/*
Tabita - 19551
Marcelle - 19534
*/

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "basket_items")
class BasketItem(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "basket_id") var basketId: Long = 0,
    @ColumnInfo(name = "meal_id") var mealId: Long = 0,
    @ColumnInfo(name = "quantity") var quantity: Int = 1
)