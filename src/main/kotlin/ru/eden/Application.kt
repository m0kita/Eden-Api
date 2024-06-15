package ru.eden

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import ru.eden.plugins.*
import ru.eden.repository.DishRepositoryImpl
import ru.eden.repository.TokenRepositoryImpl
import ru.eden.repository.UserRepositoryImpl

fun main(args: Array<String>) {
    embeddedServer(
        Netty,
        host = "0.0.0.0",
        port = 8080,
        module = Application::module
    )
}

fun Application.module() {
    val userRepository = UserRepositoryImpl()
    val tokenRepository = TokenRepositoryImpl()
    val dishRepository = DishRepositoryImpl()

    configureDatabase()
    configureSerialization(
        userRepository = userRepository,
        tokenRepository = tokenRepository,
        dishRepository = dishRepository
    )
    configureRouting()
}
