package org.example.project.feature.course_catalog.presentation.result

import org.example.project.core.common.result.Error
import org.example.project.core.common.result.FetchResult
import org.example.project.core.common.result.NetworkError
import org.example.project.core.designsystem.ui_logic.UiText
import org.example.project.core.designsystem.ui_logic.asUiText
import org.example.project.feature.course_catalog.presentation.result.FetchResultUi.*

// TODO: finish 
fun <D, D2, E : Error, C> FetchResult<D, E, C>.map(
    transform: (D) -> D2,
    transformCached: (C) -> D2
): FetchResultUi<D2> {
    return when (this) {
        is FetchResult.Success<D> -> {
            mapSuccess(this.successData, transform)
        }

//        is FetchResult.ErrorRes<E> -> {
//            mapError<E>(
//                when (this.error) {
//                    is NetworkError -> this.error.asUiText()
//                    else -> UiText.DynamicString("")
//                }
//            )
//        }

        is FetchResult.Cache<C, E> -> {
            FetchResultUi.Cached(
                transformCached(this.cacheData), when (this.cacheError) {
                    is NetworkError -> this.cacheError.asUiText()
                    else -> UiText.DynamicString("")
                }
            )
        }
    } as FetchResultUi<D2>
}

private fun <T, T2> mapSuccess(data: T, transform: (T) -> T2): FetchResultUi<T2>{
    return Success(transform(data))
}


private fun <T, T2> mapCache(data: T2, message: UiText): FetchResultUi<T2>{
    return Cached(data, message)
}

private fun <T2> mapError(message: UiText): FetchResultUi<T2>{
    return Error(message)
}
