package ru.eden.database.table

import org.jetbrains.exposed.dao.id.IntIdTable

object TokenTable : IntIdTable("token") {
    val email = varchar("email", 50)
    val token = varchar("password", 100)
}