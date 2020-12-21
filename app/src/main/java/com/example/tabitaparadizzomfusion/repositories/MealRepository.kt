package com.example.tabitaparadizzomfusion.repositories
/*
Tabita - 19551
Marcelle - 19534
*/

import com.example.tabitaparadizzomfusion.daos.MealDao
import com.example.tabitaparadizzomfusion.models.Meal

class MealRepository(
    private val mealDao: MealDao
) {
    suspend fun loadMeals(mealIds: List<Long>): List<Meal> {
        return mealDao.loadAllByIds(mealIds)
    }
}