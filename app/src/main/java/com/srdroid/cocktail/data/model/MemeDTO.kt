package com.srdroid.cocktail.data.model

import com.srdroid.cocktail.domain.model.Drink

data class MemeDTO(
    val `data`: Data,
    val success: Boolean
)

data class Data(
    val memes: List<Meme>
)

data class Meme(
    val box_count: Int,
    val height: Int,
    val id: String,
    val name: String,
    val url: String,
    val width: Int
)

fun Meme.toDomainMeme(): Drink {
    return Drink(
        id = this.id,
        name = this.name,
        image = this.url,
        width = this.width,
        height = this.height,
        imageAspectRation = String.format("%d:%d", this.width, this.height)
    )
}
