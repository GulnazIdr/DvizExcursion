package org.gulnazidr.stepik.core.designsystem.ui_logic.mapper

import org.gulnazidr.stepik.core.common.result.Mapper
import org.gulnazidr.stepik.core.model.User
import org.gulnazidr.stepik.feature.auth.presentation.models.UserUi

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