package com.example.tabitaparadizzomfusion.ui.dashboard
/*
Tabita - 19551
Marcelle - 19534
*/

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tabitaparadizzomfusion.Application
import com.example.tabitaparadizzomfusion.R
import com.example.tabitaparadizzomfusion.adapters.CategoryListAdapter

class DashboardFragment : Fragment() {

    private val dashboardViewModel: DashboardViewModel by viewModels {
        val application = (requireActivity().application as Application);
        DashboardViewModelFactory(application.categoryRepository, application.basketRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        val recyclerView = root.findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = CategoryListAdapter()

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(root.context)

        adapter.onItemClick = { meal ->
            println("From DashboardFragment $meal.name")
            dashboardViewModel.addToBasket(meal)

            Toast
                .makeText(activity, "${meal.name} was added to your basket", Toast.LENGTH_SHORT)
                .show()
        }

        dashboardViewModel.allCategories.observe(viewLifecycleOwner) { categories ->
            categories.let { adapter.submitList(it) }
        }

        return root
    }
}