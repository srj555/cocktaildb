package com.srdroid.cocktail.data.api

import com.srdroid.cocktail.data.model.DrinksModel
import com.srdroid.cocktail.data.model.MemeDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface DrinkSearchAPI {


  /*  @GET("api/json/v1/1/search.php")
    suspend fun getSearchCocktailList(
        @Query("s") query: String
    ): DrinksModel*/


    @GET("get_memes")
    suspend fun getMemes(): MemeDTO


    @GET("api/json/v1/1/lookup.php")
    suspend fun getCocktailDetails(
        @Query("i") i: String
    ): DrinksModel


}