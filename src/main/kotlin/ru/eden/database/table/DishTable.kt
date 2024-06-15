package ru.eden.database.table

import org.jetbrains.exposed.dao.id.IntIdTable

object DishTable : IntIdTable("dish") {
    val type = integer("type")
    val imageURL = varchar("imageURL", 200)
    val name = varchar("name", 50)
    val description = varchar("description", 200)
    val price = integer("price")
    val isAvailable = bool("isAvailable")
}