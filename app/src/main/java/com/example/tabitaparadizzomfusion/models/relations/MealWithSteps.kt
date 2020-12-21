package com.example.tabitaparadizzomfusion.models.relations
/*
Tabita - 19551
Marcelle - 19534
*/

import androidx.room.Embedded
import androidx.room.Relation
import com.example.tabitaparadizzomfusion.models.Meal
import com.example.tabitaparadizzomfusion.models.MealStep

data class MealWithSteps(
    @Embedded val meal: Meal,
    @Relation(
        parentColumn = "id",
        entityColumn = "meal_id"
    )
    val options: List<MealStep>
)