package ru.eden

import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import ru.eden.database.dao.TokenDAO
import ru.eden.database.dao.UserDAO
import ru.eden.model.Token
import ru.eden.model.User

suspend fun <T> suspendTransaction(block: Transaction.() -> T): T =
    newSuspendedTransaction(Dispatchers.IO, statement = block)


fun daoToModel(dao: UserDAO) = User(
    name = dao.name,
    email = dao.email,
    password = dao.password
)

fun daoToModel(dao: TokenDAO) = Token(
    token = dao.token
)