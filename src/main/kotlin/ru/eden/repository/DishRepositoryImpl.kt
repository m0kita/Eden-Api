package ru.eden.repository

import ru.eden.daoToModel
import ru.eden.database.dao.DishDAO
import ru.eden.database.table.DishTable
import ru.eden.model.Dish
import ru.eden.suspendTransaction

class DishRepositoryImpl : DishRepository {
    override suspend fun dishesByType(type: Int): List<Dish> = suspendTransaction {
        DishDAO
            .find { DishTable.type eq type }
            .map(::daoToModel)
    }

    override suspend fun dishesById(ids: List<Int>): List<Dish> = suspendTransaction {
        val result = mutableListOf<Dish>()
        ids.forEach { id ->
            val dish = DishDAO
                .find { DishTable.id eq id }
                .limit(1)
                .map(::daoToModel)
                .first()
            result.add(dish)
        }
        result
    }
}