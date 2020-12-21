package com.example.tabitaparadizzomfusion.adapters
/*
Tabita - 19551
Marcelle - 19534
*/

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tabitaparadizzomfusion.R
import com.example.tabitaparadizzomfusion.models.Meal
import com.example.tabitaparadizzomfusion.models.relations.CategoryWithMeals
import java.text.NumberFormat
import java.util.*

class CategoryListAdapter : ListAdapter<CategoryWithMeals, CategoryListAdapter.CategoryViewHolder>(
    CategoriesComparator()
) {

    var onItemClick: ((Meal) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder.create(parent, this)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class CategoryViewHolder(
        itemView: View,
        private val onItemClick: ((Meal) -> Unit)?
    ) :
        RecyclerView.ViewHolder(itemView) {

        private val currencyFormatter: NumberFormat = NumberFormat.getCurrencyInstance()
        private val categoryNameView: TextView = itemView.findViewById(R.id.categoryNameView)
        private val categoryMealsView: LinearLayout = itemView.findViewById(R.id.categoryMealsView)

        init {
            currencyFormatter.maximumFractionDigits = 2
            currencyFormatter.currency = Currency.getInstance("EUR")
        }

        fun bind(category: CategoryWithMeals) {
            categoryNameView.text = category.category.name
            category.meals.forEach { meal -> addMealView(meal) }
        }

        private fun addMealView(meal: Meal) {
            val mealView: View = LayoutInflater.from(itemView.context).inflate(
                R.layout.ir_meal,
                null
            )

            val mealName: TextView = mealView.findViewById(R.id.mealName)
            var mealPrice: TextView = mealView.findViewById(R.id.mealPrice)
            var mealDescription: TextView = mealView.findViewById(R.id.mealDescription)

            mealName.text = meal.name
            mealPrice.text = currencyFormatter.format(meal.price)
            mealDescription.text = meal.description

            mealView.setOnClickListener { view ->
                onItemClick?.invoke(meal)
            }

            categoryMealsView.addView(mealView)
        }

        companion object {
            fun create(
                parent: ViewGroup,
                categoryListAdapter: CategoryListAdapter
            ): CategoryViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.ir_category, parent, false)
                return CategoryViewHolder(view, categoryListAdapter.onItemClick)
            }
        }
    }

    class CategoriesComparator : DiffUtil.ItemCallback<CategoryWithMeals>() {
        override fun areItemsTheSame(
            oldItem: CategoryWithMeals,
            newItem: CategoryWithMeals
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: CategoryWithMeals,
            newItem: CategoryWithMeals
        ): Boolean {
            return oldItem.category.id == newItem.category.id
        }
    }
}