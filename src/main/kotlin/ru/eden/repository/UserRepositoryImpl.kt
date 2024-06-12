package ru.eden.repository

import ru.eden.daoToModel
import ru.eden.database.dao.UserDAO
import ru.eden.database.table.UserTable
import ru.eden.model.User
import ru.eden.suspendTransaction

class UserRepositoryImpl : UserRepository {
    override suspend fun addUser(user: User): Boolean = suspendTransaction {
        val userByEmail = UserDAO
            .find { (UserTable.email eq user.email) }
            .limit(1)
            .firstOrNull()

        if(userByEmail == null) {
            UserDAO.new {
                name = user.name
                email = user.email
                password = user.password
            }
            true
        } else {
            false
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