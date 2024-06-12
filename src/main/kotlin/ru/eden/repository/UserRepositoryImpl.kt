package ru.eden.repository

import ru.eden.daoToModel
import ru.eden.database.dao.UserDAO
import ru.eden.database.table.UserTable
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

    override suspend fun userByEmailAndPassword(email: String, password: String): User? = suspendTransaction {
        val user = UserDAO
            .find { (UserTable.email eq email) }
            .limit(1)
            .firstOrNull()

        if (user?.password == password) {
            daoToModel(user)
        } else {
            null
        }
    }

}