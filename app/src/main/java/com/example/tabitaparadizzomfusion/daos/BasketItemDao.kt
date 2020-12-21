package com.example.tabitaparadizzomfusion.daos
/*
Tabita - 19551
Marcelle - 19534
*/

import androidx.room.*
import com.example.tabitaparadizzomfusion.models.BasketItem

@Dao
interface BasketItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(basketItem: BasketItem)

    @Update
    suspend fun update(basketItem: BasketItem)

    @Query("SELECT * FROM basket_items WHERE meal_id = :mealId")
    suspend fun findByMealId(mealId: Long): BasketItem?

    @Query("SELECT * FROM basket_items WHERE id = :id")
    suspend fun findById(id: Long): BasketItem?

    @Query("DELETE FROM basket_items WHERE id = :id")
    suspend fun delete(id: Long)
}