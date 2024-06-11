package ru.eden.repository

import ru.eden.model.User

interface UserRepository {
    suspend fun addUser(user: User)
}