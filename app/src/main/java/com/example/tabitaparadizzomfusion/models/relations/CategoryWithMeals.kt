package com.example.tabitaparadizzomfusion.models.relations
/*
Tabita - 19551
Marcelle - 19534
*/

import androidx.room.*
import com.example.tabitaparadizzomfusion.models.Category
import com.example.tabitaparadizzomfusion.models.Meal

data class CategoryWithMeals(
    @Embedded val category: Category,
    @Relation(
        parentColumn = "id",
        entityColumn = "category_id"
    )
    val meals: List<Meal>
)