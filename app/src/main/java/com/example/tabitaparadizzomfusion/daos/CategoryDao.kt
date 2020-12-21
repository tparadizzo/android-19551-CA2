package com.example.tabitaparadizzomfusion.daos
/*
Tabita - 19551
Marcelle - 19534
*/

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tabitaparadizzomfusion.models.Category
import com.example.tabitaparadizzomfusion.models.relations.CategoryWithMeals
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Query("SELECT * FROM categories")
    fun all(): Flow<List<Category>>

    @Query("SELECT * FROM categories")
    fun allWithMeals(): Flow<List<CategoryWithMeals>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(category: Category)

    @Query("DELETE FROM categories")
    suspend fun deleteAll()
}