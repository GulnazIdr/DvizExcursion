package org.example.project.core.designsystem.ui_logic

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

sealed class UiText {
    data class DynamicString(val value: String): UiText()
    class ResourceString(
        val stringRes: StringResource
    ): UiText()

    @Composable
    fun asString(): String{
        return when(this){
            is DynamicString -> value
            is ResourceString -> stringResource(stringRes)
        }
    }
}