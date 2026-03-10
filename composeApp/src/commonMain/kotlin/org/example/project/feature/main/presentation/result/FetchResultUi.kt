package org.example.project.feature.main.presentation.result

import androidx.compose.runtime.Composable
import org.example.project.core.designsystem.UiText

interface FetchResultUi<T> {
    @Composable
    fun Display(
        onSuccess: @Composable (data: T) -> Unit,
        onError: @Composable (message: String) -> Unit,
        onLoading: @Composable () -> Unit
    )

    class Success<T>(val data: T): FetchResultUi<T>{
        @Composable
        override fun Display(
            onSuccess: @Composable ((data: T) -> Unit),
            onError: @Composable ((message: String) -> Unit),
            onLoading: @Composable (() -> Unit)
        ) {
           onSuccess(data)
        }
    }

    class Error<T>(val message: UiText): FetchResultUi<T>{
        @Composable
        override fun Display(
            onSuccess: @Composable ((data: T) -> Unit),
            onError: @Composable ((message: String) -> Unit),
            onLoading: @Composable (() -> Unit)
        ) {
            onError(message.asString())
        }
    }

    class Loading<T>(): FetchResultUi<T>{
        @Composable
        override fun Display(
            onSuccess: @Composable ((data: T) -> Unit),
            onError: @Composable ((message: String) -> Unit),
            onLoading: @Composable (() -> Unit)
        ) {
            onLoading()
        }
    }
}