package com.example.tabitaparadizzomfusion.ui.basket
/*
Tabita - 19551
Marcelle - 19534
*/

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tabitaparadizzomfusion.Application
import com.example.tabitaparadizzomfusion.R
import com.example.tabitaparadizzomfusion.adapters.BasketListAdapter

class BasketFragment : Fragment() {

    private val basketViewModel: BasketViewModel by viewModels {
        val application = (requireActivity().application as Application);
        BasketViewModelFactory(application.basketRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_basket, container, false)

        val recyclerView = root.findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = BasketListAdapter()

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(root.context)

        basketViewModel.basketWithItems.observe(viewLifecycleOwner) { basketWithItems ->
            basketWithItems.let { adapter.submitList(it.items) }
        }

        adapter.onItemAddQuantity = { basketItemWithMeal ->
            basketViewModel.addToBasket(basketItemWithMeal.meal)
        }

        adapter.onItemRemoveQuantity = { basketItemWithMeal ->
            basketViewModel.removeFromBasket(basketItemWithMeal.basketItem)
        }

        return root
    }
}