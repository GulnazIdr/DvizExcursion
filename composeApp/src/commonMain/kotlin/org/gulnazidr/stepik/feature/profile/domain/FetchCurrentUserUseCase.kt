package org.gulnazidr.stepik.feature.profile.domain

import io.github.aakira.napier.Napier
import org.gulnazidr.stepik.core.common.result.FetchDataResult
import org.gulnazidr.stepik.core.common.result.FetchError
import org.gulnazidr.stepik.core.common.result.parseExceptionToNetworkError
import org.gulnazidr.stepik.core.domain.user.UserRepository
import org.gulnazidr.stepik.core.model.User

class FetchCurrentUserUseCase(
    private val userRepository: UserRepository
) {
    private var _currentUser: User? = null
    private var _userFetchResult: FetchDataResult<User, FetchError?>? = null

    suspend operator fun invoke(isRefreshing: Boolean): FetchDataResult<User, FetchError?>{

        if (_userFetchResult != null && !isRefreshing) {
            return _userFetchResult!!
        }

        val result = userRepository.getCurrentUser()

        result.onSuccess { dataWrappingUser ->
              _currentUser = dataWrappingUser.data

            if (dataWrappingUser.isFromCache) {
                Napier.wtf("user4 ${dataWrappingUser.data}")
                _userFetchResult = FetchDataResult.Cache(
                    cacheData = dataWrappingUser.data,
                    error = parseExceptionToNetworkError(dataWrappingUser.error)
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