package org.example.project.feature.auth.domain

typealias RootError = Error
sealed interface AuthResult<E: RootError>{
    data class Success<E: RootError>(val data: Result<Boolean>): AuthResult<E>
    data class Error<E: RootError>(val error: E): AuthResult<E>
}