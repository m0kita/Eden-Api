package ru.eden.model

import kotlinx.serialization.Serializable

@Serializable
data class Dish(
    val type: Int,
    val imageURL: String,
    val name: String,
    val description: String,
    val price: Int,
    val isAvailable: Boolean
)
