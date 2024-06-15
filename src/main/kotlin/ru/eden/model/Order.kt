package ru.eden.model

import kotlinx.serialization.Serializable

@Serializable
data class Order(
    val address: String,
    val dishesPrice: Int,
    val totalPrice: Int
)
