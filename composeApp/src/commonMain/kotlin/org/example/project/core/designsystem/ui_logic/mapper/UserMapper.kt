package org.example.project.core.designsystem.ui_logic.mapper

import org.example.project.core.common.result.Mapper
import org.example.project.core.model.User
import org.example.project.feature.auth.presentation.models.UserUi

class UserUiToUserMapper() : Mapper<UserUi, User> {
    override fun map(item: UserUi): User {
        return User(
            name = item.name,
            phone = item.phone,
            password = item.password,
            email = item.email
        )
    }
}