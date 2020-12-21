package com.example.tabitaparadizzomfusion.ui.dashboard
/*
Tabita - 19551
Marcelle - 19534
*/

import androidx.lifecycle.*
import com.example.tabitaparadizzomfusion.models.BasketItem
import com.example.tabitaparadizzomfusion.models.Meal
import com.example.tabitaparadizzomfusion.models.relations.CategoryWithMeals
import com.example.tabitaparadizzomfusion.repositories.BasketRepository
import com.example.tabitaparadizzomfusion.repositories.CategoryRepository
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val categoryRepository: CategoryRepository,
    private val basketRepository: BasketRepository
) : ViewModel() {

    val allCategories: LiveData<List<CategoryWithMeals>> =
        categoryRepository.allCategories.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun addToBasket(meal: Meal) = viewModelScope.launch {
        val basketItem = BasketItem(mealId = meal.id)
        basketRepository.addItem(basketItem)
    }
}

class DashboardViewModelFactory(
    private val categoryRepository: CategoryRepository,
    private val basketRepository: BasketRepository
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DashboardViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DashboardViewModel(categoryRepository, basketRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}