package com.example.tabitaparadizzomfusion.repositories
/*
Tabita - 19551
Marcelle - 19534
*/

import com.example.tabitaparadizzomfusion.daos.CategoryDao
import com.example.tabitaparadizzomfusion.models.relations.CategoryWithMeals
import kotlinx.coroutines.flow.Flow

class CategoryRepository(private val categoryDao: CategoryDao) {
    val allCategories: Flow<List<CategoryWithMeals>> = categoryDao.allWithMeals()
}