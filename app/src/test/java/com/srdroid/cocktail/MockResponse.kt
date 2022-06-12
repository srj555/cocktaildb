package com.srdroid.cocktail

import com.srdroid.cocktail.data.model.DrinkModel
import com.srdroid.cocktail.data.model.DrinksModel

object MockResponse {

    fun getDrinksModel(): DrinksModel {
        var drinkModel = DrinkModel(
            dateModified = "",
            idDrink = "1",
            strArea = "",
            strCategory = "",
            strCreativeCommonsConfirmed = "",
            strDrinkAlternate = "",
            strImageSource = "",
            strIngredient1 = "",
            strIngredient10 = "",
            strIngredient11 = "",
            strIngredient12 = "",
            strIngredient13 = "",
            strIngredient14 = "",
            strIngredient15 = "",
            strIngredient16 = "",
            strIngredient17 = "",
            strIngredient18 = "",
            strIngredient19 = "",
            strIngredient2 = "",
            strIngredient20 = "",
            strIngredient3 = "",
            strIngredient4 = "",
            strIngredient5 = "",
            strIngredient6 = "",
            strIngredient7 = "",
            strIngredient8 = "",
            strIngredient9 = "",
            strInstructions = "",
            strDrink = "",
            strDrinkThumb = "",
            strMeasure1 = "",
            strMeasure10 = "",
            strMeasure11 = "",
            strMeasure12 = "",
            strMeasure13 = "",
            strMeasure14 = "",
            strMeasure15 = "",
            strMeasure16 = "",
            strMeasure17 = "",
            strMeasure18 = "",
            strMeasure19 = "",
            strMeasure2 = "",
            strMeasure20 = "",
            strMeasure3 = "",
            strMeasure4 = "",
            strMeasure5 = "",
            strMeasure6 = "",
            strMeasure7 = "",
            strMeasure8 = "",
            strMeasure9 = "",
            strSource = "",
            strTags = "",
            strYoutube = ""
        )

        val listDrinkModel: List<DrinkModel> = listOf(drinkModel)
        return DrinksModel(listDrinkModel)
    }
}