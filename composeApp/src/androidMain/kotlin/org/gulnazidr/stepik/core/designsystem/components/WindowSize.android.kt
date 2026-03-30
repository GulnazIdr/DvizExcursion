package org.gulnazidr.stepik.core.designsystem.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalWindowInfo

@Composable
actual fun getWindowWidth(): Int = LocalWindowInfo.current.containerDpSize.width.value.toInt()

@Composable
actual fun getWindowHeight(): Int = LocalWindowInfo.current.containerDpSize.height.value.toInt()