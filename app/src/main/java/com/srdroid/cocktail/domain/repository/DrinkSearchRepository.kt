package com.srdroid.cocktail.domain.repository

import com.srdroid.cocktail.data.model.MemeDTO

interface DrinkSearchRepository {
    suspend fun getDrinkSearch(s:String): MemeDTO
}