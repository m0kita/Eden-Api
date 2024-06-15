package ru.eden.repository

import ru.eden.model.Dish

interface DishRepository {

    suspend fun dishesByType(type: Int): List<Dish>

    suspend fun dishesById(ids: List<Int>): List<Dish>
}