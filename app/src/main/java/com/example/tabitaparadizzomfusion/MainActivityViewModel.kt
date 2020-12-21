package com.example.tabitaparadizzomfusion
/*
Tabita - 19551
Marcelle - 19534
*/

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.tabitaparadizzomfusion.models.Meal
import com.example.tabitaparadizzomfusion.models.relations.BasketWithItems
import com.example.tabitaparadizzomfusion.repositories.BasketRepository
import com.example.tabitaparadizzomfusion.repositories.MealRepository

class MainActivityViewModel(
    private val basketRepository: BasketRepository,
    private val mealRepository: MealRepository
) : ViewModel() {
    val basket: LiveData<BasketWithItems> = basketRepository.basket.asLiveData()

    suspend fun getMeals(mealIds: List<Long>): List<Meal> {
        return mealRepository.loadMeals(mealIds);
    }
}

class MainActivityViewModelFactory(
    private val basketRepository: BasketRepository,
    private val mealRepository: MealRepository
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainActivityViewModel(basketRepository, mealRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}