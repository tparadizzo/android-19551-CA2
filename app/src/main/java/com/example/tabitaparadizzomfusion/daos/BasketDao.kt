package com.example.tabitaparadizzomfusion.daos
/*
Tabita - 19551
Marcelle - 19534
*/

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tabitaparadizzomfusion.models.Basket
import com.example.tabitaparadizzomfusion.models.relations.BasketWithItems
import com.example.tabitaparadizzomfusion.models.relations.BasketWithItemsAndMeals
import kotlinx.coroutines.flow.Flow

@Dao
interface BasketDao {
    @Query("SELECT * FROM baskets")
    fun firstFlow(): Flow<BasketWithItems>

    @Query("SELECT * FROM baskets")
    fun basketWithItemsAndMeals(): Flow<BasketWithItemsAndMeals>

    @Query("SELECT * FROM baskets")
    suspend fun first(): BasketWithItems

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(basket: Basket)

    suspend fun insertWithTimestamp(basket: Basket) {
        insert(basket.apply { updatedAt = System.currentTimeMillis() })
    }

    @Query("DELETE FROM baskets")
    suspend fun deleteAll()
}