package org.example.project.core.database.mappers

import org.example.project.core.database.model.UserEntity
import org.example.project.core.model.User

fun User.toUserEntity(): UserEntity{
    return UserEntity(
        id = id,
        name = name,
        email = email,
        phone = phone
    )
}

fun UserEntity.toUser(): User{
    return User(
        id = id,
        name = name,
        email = email,
        phone = phone,
        password = ""
    )
}