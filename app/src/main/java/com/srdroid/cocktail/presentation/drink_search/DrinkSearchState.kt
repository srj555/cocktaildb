package com.srdroid.cocktail.presentation.drink_search

import com.srdroid.cocktail.domain.model.Drink

data class DrinkSearchState(
    val isLoading: Boolean = false,
    val data: List<Drink>? = null,
    val error: String = ""
)