package ru.eden.database.dao

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.eden.database.table.TokenTable

class TokenDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<TokenDAO>(TokenTable)

    var email by TokenTable.email
    var token by TokenTable.token
}