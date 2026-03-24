package org.example.project.feature.auth.data

import org.example.project.core.datastore.user.source.UserDataStoreRepository
import org.example.project.core.model.User
import org.example.project.feature.auth.domain.AuthResult
import org.example.project.feature.auth.domain.RemoteError
import org.example.project.feature.auth.domain.login.LoginRepository

class LoginRepositoryImpl(
    private val userDataStoreRepository: UserDataStoreRepository
): LoginRepository {
    override suspend fun login(
        name: String,
        password: String
    ): AuthResult<Result<Boolean>, RemoteError> {
        userDataStoreRepository.saveCurrentUser(User(
            email = name,
            password = password
        ))
        return if (name == "gulnaz" && password == "123")
            AuthResult.Success(Result.success(true))
        else
            AuthResult.Error(RemoteError.WRONG_CREDENTIALS)
    }
}