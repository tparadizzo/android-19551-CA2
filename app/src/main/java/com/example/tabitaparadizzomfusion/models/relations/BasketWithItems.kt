package com.example.tabitaparadizzomfusion.models.relations
/*
Tabita - 19551
Marcelle - 19534
*/

import androidx.room.Embedded
import androidx.room.Relation
import com.example.tabitaparadizzomfusion.models.Basket
import com.example.tabitaparadizzomfusion.models.BasketItem

data class BasketWithItems(
    @Embedded val basket: Basket,
    @Relation(
        parentColumn = "id",
        entityColumn = "basket_id"
    )
    val items: List<BasketItem>
)