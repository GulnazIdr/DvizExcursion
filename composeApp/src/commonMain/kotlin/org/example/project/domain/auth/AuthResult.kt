package org.example.project.domain.auth

typealias RootError = Error
sealed interface AuthResult<E: RootError>{
    data class Success<E: RootError>(val data: Result<Boolean>): AuthResult<E>
    data class Error<E: RootError>(val error: E): AuthResult<E>
}

//data class AuthResult(
//    val emailError: Error? = null,
//    val passwordError: PasswordError? = null,
//    val nameError: Error? = null,
//    val policyError: Error? = null,
//    val result: Result<Boolean>? = null
//)