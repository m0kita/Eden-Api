package ru.eden.repository

import ru.eden.database.dao.UserDAO
import ru.eden.model.User
import ru.eden.suspendTransaction

class UserRepositoryImpl : UserRepository {
    override suspend fun addUser(user: User) {
        suspendTransaction {
            UserDAO.new {
                name = user.name
                email = user.email
                password = user.password
            }
        }
    }

}