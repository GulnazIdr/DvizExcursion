package org.example.project.feature.course_catalog.presentation.result

import androidx.compose.runtime.Composable
import org.example.project.core.designsystem.ui_logic.UiText

interface FetchResultUi<T> {
    @Composable
    fun Display(
        onSuccess: @Composable (data: T) -> Unit,
        onError: @Composable (message: String) -> Unit,
        onLoading: @Composable () -> Unit,
        isDismissed: Boolean
    )

    class Success<T>(val data: T): FetchResultUi<T>{
        @Composable
        override fun Display(
            onSuccess: @Composable ((data: T) -> Unit),
            onError: @Composable ((message: String) -> Unit),
            onLoading: @Composable (() -> Unit),
            isDismissed: Boolean
        ) {
           onSuccess(data)
        }
    }

    class Error<T>(val message: UiText): FetchResultUi<T>{
        @Composable
        override fun Display(
            onSuccess: @Composable ((data: T) -> Unit),
            onError: @Composable ((message: String) -> Unit),
            onLoading: @Composable (() -> Unit),
            isDismissed: Boolean
        ) {
            if(!isDismissed)
                onError(message.asString())
        }
    }

    class Cached<T>(val cacheData: T?, val reason: UiText): FetchResultUi<T>{
        @Composable
        override fun Display(
            onSuccess: @Composable ((data: T) -> Unit),
            onError: @Composable ((message: String) -> Unit),
            onLoading: @Composable (() -> Unit),
            isDismissed: Boolean
        ) {
            if (cacheData != null)
                onSuccess(cacheData)

            if(!isDismissed)
                onError(reason.asString())
        }
    }

    class Loading<T>(): FetchResultUi<T>{
        @Composable
        override fun Display(
            onSuccess: @Composable ((data: T) -> Unit),
            onError: @Composable ((message: String) -> Unit),
            onLoading: @Composable (() -> Unit),
            isDismissed: Boolean
        ) {
            onLoading()
        }
    }
}