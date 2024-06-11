package ru.eden.plugins

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.eden.model.User
import ru.eden.repository.TokenRepository
import ru.eden.repository.UserRepository

fun Application.configureSerialization(
    userRepository: UserRepository,
    tokenRepository: TokenRepository,
) {
    install(ContentNegotiation) {
        json()
    }
    routing {
        route("/users") {
            post("/registration") {
                val user = call.receive<User>()

                val token = tokenRepository.addToken(email = user.email)
                userRepository.addUser(user)

                call.respond(token)
            }
        }
    }
}
