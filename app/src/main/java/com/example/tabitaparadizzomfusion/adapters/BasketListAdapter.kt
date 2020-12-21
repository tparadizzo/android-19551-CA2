package com.example.tabitaparadizzomfusion.adapters
/*
Tabita - 19551
Marcelle - 19534
*/

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tabitaparadizzomfusion.R
import com.example.tabitaparadizzomfusion.models.relations.BasketItemWithMeal
import java.text.NumberFormat
import java.util.*

class BasketListAdapter : ListAdapter<BasketItemWithMeal, BasketListAdapter.BasketItemViewHolder>(
    BasketItemComparator()
) {

    var onItemAddQuantity: ((BasketItemWithMeal) -> Unit)? = null
    var onItemRemoveQuantity: ((BasketItemWithMeal) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BasketItemViewHolder {
        return BasketItemViewHolder.create(parent, this)
    }

    override fun onBindViewHolder(holder: BasketItemViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class BasketItemViewHolder(
        itemView: View,
        private val onItemAddQuantity: ((BasketItemWithMeal) -> Unit)?,
        private val onItemRemoveQuantity: ((BasketItemWithMeal) -> Unit)?
    ) :
        RecyclerView.ViewHolder(itemView) {

        private val currencyFormatter: NumberFormat = NumberFormat.getCurrencyInstance()
        private val basketItemMealNameView: TextView = itemView.findViewById(R.id.basketItemMealNameView)
        private val basketItemMealPriceView: TextView = itemView.findViewById(R.id.basketItemMealPriceView)
        private val basketItemQuantityView: TextView = itemView.findViewById(R.id.basketItemQuantityView)
        private val basketItemAmountView: TextView = itemView.findViewById(R.id.basketItemAmountView)
        private val addButton: ImageButton = itemView.findViewById(R.id.addButton)
        private val removeButton: ImageButton = itemView.findViewById(R.id.removeButton)

        init {
            currencyFormatter.maximumFractionDigits = 2
            currencyFormatter.currency = Currency.getInstance("EUR")
        }

        fun bind(basketItem: BasketItemWithMeal) {
            basketItemMealNameView.text = basketItem.meal.name
            basketItemMealPriceView.text = currencyFormatter.format(basketItem.meal.price)
            basketItemQuantityView.text = basketItem.basketItem.quantity.toString();
            basketItemAmountView.text = currencyFormatter.format(basketItem.meal.price * basketItem.basketItem.quantity)
            addButton.setOnClickListener {
                onItemAddQuantity?.invoke(basketItem)
            }
            removeButton.setOnClickListener {
                onItemRemoveQuantity?.invoke(basketItem)
            }
        }

        companion object {
            fun create(
                parent: ViewGroup,
                basketListAdapter: BasketListAdapter
            ): BasketItemViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.ir_basket_item, parent, false)

                return BasketItemViewHolder(
                    view,
                    basketListAdapter.onItemAddQuantity,
                    basketListAdapter.onItemRemoveQuantity
                )
            }
        }
    }

    class BasketItemComparator : DiffUtil.ItemCallback<BasketItemWithMeal>() {
        override fun areItemsTheSame(
            oldItem: BasketItemWithMeal,
            newItem: BasketItemWithMeal
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: BasketItemWithMeal,
            newItem: BasketItemWithMeal
        ): Boolean {
            return oldItem.basketItem.id == newItem.basketItem.id
        }
    }

}