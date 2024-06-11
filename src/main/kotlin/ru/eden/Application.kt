package ru.eden

import io.ktor.server.application.*
import ru.eden.plugins.*
import ru.eden.repository.TokenRepositoryImpl
import ru.eden.repository.UserRepositoryImpl

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    val userRepository = UserRepositoryImpl()
    val tokenRepository = TokenRepositoryImpl()


    configureSerialization(
        userRepository = userRepository,
        tokenRepository = tokenRepository
    )
    configureRouting()
}
