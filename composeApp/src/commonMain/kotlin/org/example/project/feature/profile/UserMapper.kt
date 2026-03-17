package org.example.project.feature.profile

import org.example.project.feature.auth.presentation.models.UserUi
import org.example.project.core.model.User

fun User.toUserUi(): UserUi{
    return UserUi(
        name = name,
        phone = phone,
        password = password,
        email = email
    )
}

fun UserUi.toUser(): User{
    return User(
        name = name,
        phone = phone,
        password = password,
        email = email
    )
}