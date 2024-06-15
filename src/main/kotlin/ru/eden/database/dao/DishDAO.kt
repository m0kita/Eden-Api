package ru.eden.database.dao

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.eden.database.table.DishTable

class DishDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<DishDAO>(DishTable)

    var type by DishTable.type
    var imageURL by DishTable.imageURL
    var name by DishTable.name
    var description by DishTable.description
    var price by DishTable.price
    var isAvailable by DishTable.isAvailable
}