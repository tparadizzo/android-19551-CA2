package com.example.tabitaparadizzomfusion.models.relations
/*
Tabita - 19551
Marcelle - 19534
*/

import androidx.room.Embedded
import androidx.room.Relation
import com.example.tabitaparadizzomfusion.models.Basket
import com.example.tabitaparadizzomfusion.models.BasketItem

data class BasketWithItemsAndMeals (
    @Embedded val basket: Basket,
    @Relation(
        entity = BasketItem::class,
        parentColumn = "id",
        entityColumn = "basket_id"
    )
    val items: List<BasketItemWithMeal>
)