package com.srdroid.cocktail.domain.model

data class Drink(
    val id: String,
    val name: String,
    val image: String,
    val width: Int,
    val height: Int,
    val imageAspectRation: String,
)