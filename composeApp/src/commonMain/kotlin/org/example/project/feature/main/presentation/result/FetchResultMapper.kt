package org.example.project.feature.main.presentation.result

import org.example.project.core.common.result.Error
import org.example.project.core.common.result.FetchResult
import org.example.project.core.common.result.NetworkError
import org.example.project.core.designsystem.UiText
import org.example.project.core.designsystem.asUiText
import org.example.project.feature.main.presentation.result.FetchResultUi.*

fun <D, F, E : Error> FetchResult<D, E>.map(transform: (D) -> F): FetchResultUi<F>{
    return when(this){
        is FetchResult.Success<D> -> {
           mapSuccess(this.successData, transform)
        }

        is FetchResult.ErrorRes<E> -> {
            mapError<D, F>(
                when(this.error){
                    is NetworkError -> this.error.asUiText()
                    else -> UiText.DynamicString("")
                }
            )
        }
    }
}

private fun <T, F> mapSuccess(data: T, transform: (T) -> F): FetchResultUi<F>{
    return Success(transform(data))
}

private fun <T, F> mapError(message: UiText): FetchResultUi<F>{
    return Error(message)
}
