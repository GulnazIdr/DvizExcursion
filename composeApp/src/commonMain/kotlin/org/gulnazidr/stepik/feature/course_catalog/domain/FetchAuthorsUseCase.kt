package org.gulnazidr.stepik.feature.course_catalog.domain

import org.gulnazidr.stepik.core.model.User
import org.gulnazidr.stepik.core.network.ktor.user.source.KtorUserRepository

class FetchAuthorsUseCase(
    private val remoteUserRepository: KtorUserRepository
) {
    private var _authorList: List<User> = emptyList()

    suspend operator fun invoke(idList: List<Int>): List<User>{
        _authorList = emptyList()
        val result = remoteUserRepository.getUserList(idList)

        result.onSuccess { ktorUser ->
            _authorList = ktorUser
        }.onFailure {
            _authorList = emptyList()
        }

        return _authorList
    }
}