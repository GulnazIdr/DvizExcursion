package org.example.project.feature.auth.domain.login

class LoginUseCase(
    private val loginRepository: LoginRepository
) {
    suspend operator fun invoke(userName: String, password: String)
    = loginRepository.login(userName, password)
}