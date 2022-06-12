package com.srdroid.cocktail.data.repository

import com.srdroid.cocktail.data.api.DrinkSearchAPI
import com.srdroid.cocktail.data.model.DrinksModel
import com.srdroid.cocktail.domain.repository.DrinkDetailsRepository

class DrinkDetailsRepositoryImpl(private val drinkSearchAPI: DrinkSearchAPI) :
    DrinkDetailsRepository {

    override suspend fun getDrinkDetails(id: String): DrinksModel {
        return drinkSearchAPI.getCocktailDetails(id)
    }
}