package ru.eden.plugins

import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.eden.daoToModel
import ru.eden.database.dao.OrderDAO
import ru.eden.model.LoginData
import ru.eden.model.Order
import ru.eden.model.User
import ru.eden.repository.DishRepository
import ru.eden.repository.TokenRepository
import ru.eden.repository.UserRepository
import ru.eden.suspendTransaction

fun Application.configureSerialization(
    userRepository: UserRepository,
    tokenRepository: TokenRepository,
    dishRepository: DishRepository
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
        route("/dishes") {
            get {
                val type: Int = call.request.queryParameters["type"]?.toInt() ?: 1

                val dishes = dishRepository.dishesByType(type)
                call.respond(dishes)
            }

            post("/dishesById") {
                val ids = call.receive<List<Int>>()

                val dishes = dishRepository.dishesById(ids)
                call.respond(dishes)
            }
        }
        route("/order") {
            get {
                val order = suspendTransaction {
                    OrderDAO.all().last()
                }
                call.respond(order)
            }
            post("/create") {
                val order = call.receive<Order>()
                val newOrder = suspendTransaction {
                    return@suspendTransaction OrderDAO.new {
                        address = order.address
                        dishesPrice = order.dishesPrice
                        totalPrice = order.totalPrice
                    }
                }
                call.respond(newOrder.address.isNotEmpty())
            }
        }
    }
}
