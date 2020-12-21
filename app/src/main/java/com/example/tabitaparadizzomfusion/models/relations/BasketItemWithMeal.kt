package com.example.tabitaparadizzomfusion.models.relations
/*
Tabita - 19551
Marcelle - 19534
*/

import androidx.room.Embedded
import androidx.room.Relation
import com.example.tabitaparadizzomfusion.models.BasketItem
import com.example.tabitaparadizzomfusion.models.Meal

data class BasketItemWithMeal(
    @Embedded val basketItem: BasketItem,
    @Relation(
        parentColumn = "meal_id",
        entityColumn = "id"
    )
    val meal: Meal
)