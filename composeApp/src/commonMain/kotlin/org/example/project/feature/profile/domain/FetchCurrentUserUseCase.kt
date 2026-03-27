package org.example.project.feature.profile.domain

import org.example.project.core.common.result.FetchDataResult
import org.example.project.core.common.result.NetworkError
import org.example.project.core.common.result.parseExceptionToNetworkError
import org.example.project.core.model.User
import org.example.project.core.network.ktor.user.source.KtorUserRepository

class FetchCurrentUserUseCase(
    private val remoteUserRepository: KtorUserRepository
) {
    private var _currentUser: User? = null
    private var _userFetchResult: FetchDataResult<User, NetworkError?>? = null

    suspend operator fun invoke(): FetchDataResult<User, NetworkError?>{

        if (_userFetchResult != null) {
            return _userFetchResult!!
        }

        val result = remoteUserRepository.getCurrentUser()

        result.onSuccess { ktorUser ->
              _currentUser = ktorUser.data

            if (ktorUser.isFromCache) {
                _userFetchResult = FetchDataResult.Cache(
                    cacheData = ktorUser.data,
                    error = parseExceptionToNetworkError(ktorUser.error)
                )
            } else {
                _userFetchResult = FetchDataResult.Success(
                    data = _currentUser!!
                )
            }
        }.onFailure { exception ->
            _userFetchResult = FetchDataResult.Error(
                error = parseExceptionToNetworkError(exception)!!
            )
        }

        return _userFetchResult!!
    }
}