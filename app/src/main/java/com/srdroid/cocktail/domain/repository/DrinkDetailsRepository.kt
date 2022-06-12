package com.srdroid.cocktail.domain.repository

import com.srdroid.cocktail.data.model.DrinksModel

interface DrinkDetailsRepository {
    suspend fun getDrinkDetails(id:String): DrinksModel
}