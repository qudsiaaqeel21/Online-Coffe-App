package com.example.onlinecoffeapp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CoffeeViewModel : ViewModel() {

    // Saari coffees ki list
    private val _allCoffees = MutableStateFlow<List<Coffee>>(
        listOf(
            Coffee(1, "Espresso", "Strong and rich", 500.0, R.drawable.espresso),
            Coffee(2, "Latte", "Smooth and creamy", 300.0, R.drawable.latte),
            Coffee(3, "Mocha", "Chocolate coffee", 400.0, R.drawable.mocha),
            Coffee(4, "Cappuccino", "Foamy and bold", 350.0, R.drawable.cappucino)
        )
    )
    val allCoffees: StateFlow<List<Coffee>> = _allCoffees.asStateFlow()

    // Favourites ki list - ye auto update hogi
    private val _favouriteCoffees = MutableStateFlow<List<Coffee>>(emptyList())
    val favouriteCoffees: StateFlow<List<Coffee>> = _favouriteCoffees.asStateFlow()

    fun toggleFavourite(coffee: Coffee) {
        // 1. All coffees list me isFavourite update karo
        _allCoffees.update { currentList ->
            currentList.map {
                if (it.id == coffee.id) it.copy(isFavourite = !it.isFavourite)
                else it
            }
        }

        // 2. Favourites list update karo
        _favouriteCoffees.update { currentFavs ->
            if (currentFavs.any { it.id == coffee.id }) {
                currentFavs.filter { it.id != coffee.id }
            } else {
                currentFavs + coffee.copy(isFavourite = true)
            }
        }
    }
}