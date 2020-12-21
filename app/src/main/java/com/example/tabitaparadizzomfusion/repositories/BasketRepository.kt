package com.example.tabitaparadizzomfusion.repositories
/*
Tabita - 19551
Marcelle - 19534
*/

import androidx.annotation.WorkerThread
import com.example.tabitaparadizzomfusion.daos.BasketDao
import com.example.tabitaparadizzomfusion.daos.BasketItemDao
import com.example.tabitaparadizzomfusion.models.BasketItem
import com.example.tabitaparadizzomfusion.models.relations.BasketWithItems
import com.example.tabitaparadizzomfusion.models.relations.BasketWithItemsAndMeals
import kotlinx.coroutines.flow.Flow

class BasketRepository(
    private val basketDao: BasketDao,
    private val basketItemDao: BasketItemDao
) {
    val basket: Flow<BasketWithItems> = basketDao.firstFlow()
    val basketItemsWithMeal: Flow<BasketWithItemsAndMeals> = basketDao.basketWithItemsAndMeals()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun addItem(basketItem: BasketItem) {
        val basket = basketDao.first()
        val bi: BasketItem? = basketItemDao.findByMealId(basketItem.mealId)

        if (bi != null) {
            bi.quantity++
            basketItemDao.update(bi)
        }
        else {
            basketItem.basketId = basket.basket.id
            basketItemDao.insert(basketItem)
        }
    }

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun removeItem(basketItem: BasketItem) {
        val bi: BasketItem? = basketItemDao.findById(basketItem.id)

        if (bi != null) {
            if (bi.quantity == 1) {
                basketItemDao.delete(bi.id)
            }
            else {
                bi.quantity--
                basketItemDao.update(bi)
            }
        }
    }
}