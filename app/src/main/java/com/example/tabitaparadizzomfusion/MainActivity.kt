package com.example.tabitaparadizzomfusion
/*
Tabita - 19551
Marcelle - 19534
*/

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.tabitaparadizzomfusion.models.BasketItem
import com.example.tabitaparadizzomfusion.models.Meal
import com.example.tabitaparadizzomfusion.models.relations.BasketWithItems
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels {
        val application = (application as Application);
        MainActivityViewModelFactory(application.basketRepository, application.mealRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        viewModel.basket.observe(this) { t: BasketWithItems? ->
            if (t != null) {
                updateBasketTotal(t)
            }
        }

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_basket
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    fun navigateToBasket(view: View) {
        val navController = findNavController(R.id.nav_host_fragment)
        navController.navigate(R.id.navigation_basket)
    }

    fun navigateToMenu(view: View) {
        val navController = findNavController(R.id.nav_host_fragment)
        navController.navigate(R.id.navigation_dashboard)
    }

    private fun updateBasketTotal(basketWithItems: BasketWithItems) {
        println("Basket with id ${basketWithItems.basket.id} updated with ${basketWithItems.items.size} items")
        val mealIds = basketWithItems.items.map { basketItem -> basketItem.mealId }

        lifecycleScope.launch {
            val meals: List<Meal> = viewModel.getMeals(mealIds)
            var total: Double = 0.0

            if (!meals.isEmpty()) {
                basketWithItems.items.forEach { basketItem: BasketItem ->
                    val meal: Meal? = meals.find { meal -> meal.id == basketItem.mealId }
                    if (meal != null) {
                        total += meal.price * basketItem.quantity
                    }
                }
            }

            var basketTotalView: TextView = findViewById(R.id.basketTotal)
            basketTotalView.text = (application as Application).currencyFormatter.format(total)
        }
    }
}