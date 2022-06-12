package com.srdroid.cocktail.presentation.drink_details

import com.srdroid.cocktail.domain.model.DrinkDetails

data class DrinkDetailsState(
    val isLoading: Boolean = false,
    val data: DrinkDetails? = null,
    val error: String = ""
)