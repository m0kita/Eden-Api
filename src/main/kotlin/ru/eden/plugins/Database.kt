package ru.eden.plugins

import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database

fun Application.configureDatabase() {
    Database.connect("jdbc:postgresql://localhost:12346/test", driver = "org.postgresql.Driver",
        user = "root", password = "your_pwd")
}