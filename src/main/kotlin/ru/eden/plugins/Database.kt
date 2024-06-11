package ru.eden.plugins

import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database

fun Application.configureDatabase() {
    Database.connect(
        url = environment.config.property("ktor.deployment.databaseURL").getString(),
        driver = "org.postgresql.Driver",
        user = environment.config.property("ktor.deployment.databaseUser").getString(),
        password = environment.config.property("ktor.deployment.databasePassword").getString()
    )
}