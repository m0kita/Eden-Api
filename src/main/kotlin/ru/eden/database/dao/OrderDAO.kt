package ru.eden.database.dao

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.eden.database.table.OrderTable

class OrderDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<OrderDAO>(OrderTable)

    var address by OrderTable.address
    var dishesPrice by OrderTable.dishesPrice
    var totalPrice by OrderTable.totalPrice
}