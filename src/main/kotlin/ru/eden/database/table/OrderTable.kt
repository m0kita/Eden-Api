package ru.eden.database.table

import org.jetbrains.exposed.dao.id.IntIdTable

object OrderTable : IntIdTable("order") {
    val address = varchar("address", 100)
    val dishesPrice = integer("dishesPrice")
    val totalPrice = integer("totalPrice")
}