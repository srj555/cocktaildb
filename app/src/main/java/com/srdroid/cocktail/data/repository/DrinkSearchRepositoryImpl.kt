package com.srdroid.cocktail.data.repository

import com.srdroid.cocktail.data.api.DrinkSearchAPI
import com.srdroid.cocktail.data.model.DrinksModel
import com.srdroid.cocktail.data.model.MemeDTO
import com.srdroid.cocktail.domain.repository.DrinkSearchRepository

class DrinkSearchRepositoryImpl(private val drinkSearchAPI: DrinkSearchAPI) :
    DrinkSearchRepository {

    override suspend fun getDrinkSearch(s: String): MemeDTO {
        return drinkSearchAPI.getMemes()
    }
}