package com.srdroid.cocktail.domain.repository

import com.srdroid.cocktail.data.model.DrinksModel

interface DrinkSearchRepository {
    suspend fun getDrinkSearch(s:String): DrinksModel
}