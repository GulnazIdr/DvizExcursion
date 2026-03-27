package org.example.project.core.domain.courses

import org.example.project.core.model.User
import org.example.project.core.network.ktor.user.RemoteUserRepository

class FetchAuthorsUseCase(
    private val remoteUserRepository: RemoteUserRepository
) {
    private var _authorList: List<User> = emptyList()

    suspend operator fun invoke(idList: List<Int>): List<User>{
        _authorList = emptyList()
        val result = remoteUserRepository.getUserList(idList)

        result.onSuccess { ktorUser ->
            _authorList = ktorUser.data
        }.onFailure {
            _authorList = emptyList()
        }

        return _authorList
    }
}