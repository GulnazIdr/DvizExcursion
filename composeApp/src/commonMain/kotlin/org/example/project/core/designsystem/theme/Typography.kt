package org.example.project.core.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import stepik.composeapp.generated.resources.Inter_18pt_Black
import stepik.composeapp.generated.resources.Inter_18pt_Bold
import stepik.composeapp.generated.resources.Inter_18pt_Light
import stepik.composeapp.generated.resources.Inter_18pt_Medium
import stepik.composeapp.generated.resources.Inter_18pt_Regular
import stepik.composeapp.generated.resources.Inter_18pt_SemiBold
import stepik.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.Font
import  androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

val Inter @Composable get() = FontFamily(
    Font(
        resource = Res.font.Inter_18pt_Light,
        weight = FontWeight.Light
    ),
    Font(
        resource = Res.font.Inter_18pt_Regular,
        weight = FontWeight.Normal
    ),
    Font(
        resource = Res.font.Inter_18pt_Medium,
        weight = FontWeight.Medium
    ),
    Font(
        resource = Res.font.Inter_18pt_SemiBold,
        weight = FontWeight.SemiBold
    ),
    Font(
        resource = Res.font.Inter_18pt_Bold,
        weight = FontWeight.Bold
    ),
    Font(
        resource = Res.font.Inter_18pt_Black,
        weight = FontWeight.Black
    ),
)

val Typography @Composable get() : Typography = Typography(
    bodyMedium = TextStyle(
        fontSize = 17.sp,
        fontFamily = Inter,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 20.sp,
        color = MaterialTheme.colorScheme.onSecondaryContainer
    ),
    bodyLarge = TextStyle(
        fontSize = 24.sp,
        fontFamily = Inter,
        fontWeight = FontWeight.Bold,
        lineHeight = 24.sp,
        color = MaterialTheme.colorScheme.onSecondaryContainer
    ),
    bodySmall = TextStyle(
        fontSize = 15.sp,
        fontFamily = Inter,
        fontWeight = FontWeight.Normal,
        lineHeight = 20.sp,
        color = MaterialTheme.colorScheme.onSecondaryContainer
    )
)