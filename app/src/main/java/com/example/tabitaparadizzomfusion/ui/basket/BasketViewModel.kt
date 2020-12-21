package com.example.tabitaparadizzomfusion.ui.basket
/*
Tabita - 19551
Marcelle - 19534
*/

import androidx.lifecycle.*
import com.example.tabitaparadizzomfusion.models.Basket
import com.example.tabitaparadizzomfusion.models.BasketItem
import com.example.tabitaparadizzomfusion.models.Meal
import com.example.tabitaparadizzomfusion.models.relations.BasketWithItems
import com.example.tabitaparadizzomfusion.models.relations.BasketWithItemsAndMeals
import com.example.tabitaparadizzomfusion.models.relations.CategoryWithMeals
import com.example.tabitaparadizzomfusion.repositories.BasketRepository
import com.example.tabitaparadizzomfusion.repositories.CategoryRepository
import com.example.tabitaparadizzomfusion.ui.dashboard.DashboardViewModel
import kotlinx.coroutines.launch

class BasketViewModel(
    private val basketRepository: BasketRepository
) : ViewModel() {

    val basketWithItems: LiveData<BasketWithItemsAndMeals> = basketRepository.basketItemsWithMeal.asLiveData()

    fun addToBasket(meal: Meal) = viewModelScope.launch {
        val basketItem = BasketItem(mealId = meal.id)
        basketRepository.addItem(basketItem)
    }

    fun removeFromBasket(basketItem: BasketItem) = viewModelScope.launch {
        basketRepository.removeItem(basketItem)
    }
}

class BasketViewModelFactory(
    private val basketRepository: BasketRepository
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BasketViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BasketViewModel(basketRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}