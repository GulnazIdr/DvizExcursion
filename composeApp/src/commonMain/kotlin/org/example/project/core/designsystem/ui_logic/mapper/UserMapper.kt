package org.example.project.core.designsystem.ui_logic.mapper

import org.example.project.core.common.result.Mapper
import org.example.project.core.model.User
import org.example.project.feature.auth.presentation.models.UserUi

class UserUiToUserMapper() : Mapper<UserUi, User> {
    override fun map(item: UserUi): User {
        return User(
            id = item.id,
            name = item.name,
            details = item.details,
            shortBio = item.shortBio,
            profileImg = item.profileImg,
            email = item.email,
            phone = item.phone
        )
    }
}

class UserToUserUiMapper(): Mapper<User, UserUi>{
    override fun map(item: User): UserUi {
        return UserUi(
            id = item.id,
            name = item.name,
            details = item.details,
            shortBio = item.shortBio,
            profileImg = item.profileImg,
            email = item.email,
            phone = item.phone
        )
    }
}