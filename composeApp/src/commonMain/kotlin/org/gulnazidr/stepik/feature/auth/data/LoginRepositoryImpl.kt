package org.gulnazidr.stepik.feature.auth.data

import org.gulnazidr.stepik.core.domain.user.UserRepository
import org.gulnazidr.stepik.core.model.User
import org.gulnazidr.stepik.feature.auth.domain.login.LoginRepository
import org.gulnazidr.stepik.feature.auth.domain.result.AuthError
import org.gulnazidr.stepik.feature.auth.domain.result.AuthResult
import org.gulnazidr.stepik.feature.auth.domain.result.LocalError
import org.gulnazidr.stepik.feature.auth.domain.result.RemoteError

class LoginRepositoryImpl(
    private val userRepository: UserRepository
): LoginRepository {
    override suspend fun login(
        name: String,
        password: String
    ): AuthResult<Result<Boolean>, AuthError> {
        val saveRes = userRepository.saveUser(User(
            email = name,
            password = password
        ))
        return if (saveRes) {
            if (name == "gulnaz" && password == "123") {
                AuthResult.Success(Result.success(true))
            }else{
                AuthResult.Error(RemoteError.WRONG_CREDENTIALS)
            }
        }else {
            AuthResult.Error(LocalError.DataStoreError.SAVE_USER)
        }
    }
}