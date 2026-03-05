package org.example.project.presentation.components

import androidx.compose.runtime.Composable
import java.awt.Toolkit

@Composable
actual fun getWindowWidth(): Int = Toolkit.getDefaultToolkit().screenSize.width

@Composable
actual fun getWindowHeight(): Int = Toolkit.getDefaultToolkit().screenSize.height