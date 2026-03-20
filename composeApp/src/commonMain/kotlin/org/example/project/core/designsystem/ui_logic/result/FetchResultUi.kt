package org.example.project.core.designsystem.ui_logic.result

import androidx.compose.runtime.Composable
import io.github.aakira.napier.Napier
import org.example.project.core.designsystem.ui_logic.UiText

interface FetchResultUi<T> {
    @Composable
    fun Display(
        onSuccess: @Composable (data: T) -> Unit,
        onError: @Composable (message: String) -> Unit,
        onLoading: @Composable () -> Unit,
        isDismissed: Boolean
    )

    class Success<T>(val data: T) : FetchResultUi<T> {
        @Composable
        override fun Display(
            onSuccess: @Composable ((data: T) -> Unit),
            onError: @Composable (message: String) -> Unit,
            onLoading: @Composable (() -> Unit),
            isDismissed: Boolean
        ) {
            onSuccess(data)
        }
    }

    class Error<T>(
        val message: UiText,
        val cacheData: T? = null
    ) : FetchResultUi<T> {
        @Composable
        override fun Display(
            onSuccess: @Composable ((data: T) -> Unit),
            onError: @Composable (message: String) -> Unit,
            onLoading: @Composable (() -> Unit),
            isDismissed: Boolean
        ) {
            if (!isDismissed && message.asString().isNotEmpty()) {
                onError(message.asString())
            }
            if (cacheData != null) {
                onSuccess(cacheData)
            }
        }
    }

    class Loading<T>() : FetchResultUi<T> {
        @Composable
        override fun Display(
            onSuccess: @Composable ((data: T) -> Unit),
            onError: @Composable (message: String) -> Unit,
            onLoading: @Composable (() -> Unit),
            isDismissed: Boolean
        ) {
            onLoading()
        }
    }
}