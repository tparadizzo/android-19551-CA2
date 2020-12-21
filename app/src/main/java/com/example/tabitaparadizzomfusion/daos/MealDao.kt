package com.example.tabitaparadizzomfusion.daos
/*
Tabita - 19551
Marcelle - 19534
*/

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tabitaparadizzomfusion.models.relations.CategoryWithMeals
import com.example.tabitaparadizzomfusion.models.Meal
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDao {
    @Query("SELECT * FROM meals")
    fun all(): Flow<List<Meal>>

    @Query("SELECT * FROM meals WHERE category_id = :category")
    fun allFromCategory(category: Long): Flow<List<CategoryWithMeals>>

    @Query("SELECT * FROM meals WHERE id IN (:mealIds)")
    suspend fun loadAllByIds(mealIds: List<Long>): List<Meal>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(meal: Meal)

    @Query("DELETE FROM meals")
    suspend fun deleteAll()
}