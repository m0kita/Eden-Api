package ru.eden.repository

import ru.eden.daoToModel
import ru.eden.database.dao.TokenDAO
import ru.eden.database.table.TokenTable
import ru.eden.model.Token
import ru.eden.suspendTransaction
import java.util.UUID

class TokenRepositoryImpl : TokenRepository {
    override suspend fun tokenByEmail(email: String): Token = suspendTransaction {
        TokenDAO
            .find { (TokenTable.email eq email) }
            .limit(1)
            .map(::daoToModel)
            .first()
    }

    override suspend fun addToken(email: String): Token = suspendTransaction {
        val token = TokenDAO.new {
            this.email = email
            this.token = UUID.randomUUID().toString()
        }

        daoToModel(token)
    }
}