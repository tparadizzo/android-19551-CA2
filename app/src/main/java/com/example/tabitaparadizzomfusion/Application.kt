package com.example.tabitaparadizzomfusion
/*
Tabita - 19551
Marcelle - 19534
*/

import android.app.Application
import com.example.tabitaparadizzomfusion.repositories.BasketRepository
import com.example.tabitaparadizzomfusion.repositories.CategoryRepository
import com.example.tabitaparadizzomfusion.repositories.MealRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import java.text.NumberFormat
import java.util.*

class Application: Application() {
    // No need to cancel this scope as it'll be torn down with the process
    val applicationScope = CoroutineScope(SupervisorJob())
    val currencyFormatter: NumberFormat = NumberFormat.getCurrencyInstance()

    init {
        currencyFormatter.maximumFractionDigits = 2
        currencyFormatter.currency = Currency.getInstance("EUR")
    }

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy {
        AppDatabase.getDatabase(this, applicationScope)
    }

    val categoryRepository by lazy {
        CategoryRepository(database.categoryDao())
    }

    val basketRepository by lazy {
        BasketRepository(database.basketDao(), database.basketItemDao())
    }

    val mealRepository by lazy {
        MealRepository(database.mealDao())
    }
}