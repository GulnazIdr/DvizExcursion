package org.gulnazidr.stepik.feature.auth.data

import org.gulnazidr.stepik.core.domain.user.UserRepository
import org.gulnazidr.stepik.feature.auth.domain.result.AuthResult
import org.gulnazidr.stepik.feature.auth.domain.result.RemoteError
import org.gulnazidr.stepik.core.model.User
import org.gulnazidr.stepik.feature.auth.domain.registration.RegisterRepository
import org.gulnazidr.stepik.feature.auth.domain.result.AuthError
import org.gulnazidr.stepik.feature.auth.domain.result.LocalError
import org.gulnazidr.stepik.feature.profile.domain.local.LocalUserRepository

class RegisterRepositoryImpl (
    private val userRepository: UserRepository
):  RegisterRepository{

    override suspend fun signup(user: User): AuthResult<Result<Boolean>, AuthError> {
        val saveRes = userRepository.saveUser(user)
        return if (saveRes){
            AuthResult.Success(Result.success(true))
        }else{
            AuthResult.Error(LocalError.DataStoreError.SAVE_USER)
        }
    }
}