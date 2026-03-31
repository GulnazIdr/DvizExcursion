package org.gulnazidr.stepik.feature.auth.domain.result

typealias RootError = AuthError
sealed interface AuthResult<D, E: RootError>{
    data class Success<D, E: RootError>(val data: D): AuthResult<D, E>
    data class Error<D, E: RootError>(val error: E): AuthResult<D, E>
}