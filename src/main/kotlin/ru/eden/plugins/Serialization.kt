package ru.eden.plugins

import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.eden.daoToModel
import ru.eden.model.LoginData
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

                val isUserCreated = userRepository.addUser(user)
                if(isUserCreated) {
                    val token = tokenRepository.addToken(email = user.email)
                    call.respond(token)
                } else {
                    call.respond(HttpStatusCode.Conflict, "Пользователь с таким Email уже существует.")
                }
            }

            post("/login") {
                val loginData = call.receive<LoginData>()

                val user = userRepository.userByEmailAndPassword(
                    email = loginData.email,
                    password = loginData.password
                )

                if(user != null) {
                    val token = tokenRepository.tokenByEmail(email = user.email)
                    call.respond(token)
                } else {
                    call.respond(HttpStatusCode.BadRequest, "Пользователь не найден.")
                }
            }
        }
    }
}
