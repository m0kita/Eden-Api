package ru.eden.repository

import ru.eden.model.Token

interface TokenRepository {

    suspend fun tokenByEmail(email: String): Token

    suspend fun addToken(email: String): Token
}