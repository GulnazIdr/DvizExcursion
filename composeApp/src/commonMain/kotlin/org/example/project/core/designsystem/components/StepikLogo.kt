package org.example.project.core.designsystem.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import stepik.composeapp.generated.resources.Res
import stepik.composeapp.generated.resources.stepik_logo
import org.jetbrains.compose.resources.painterResource

@Composable
fun StepikLogo(
    size: Dp,
    modifier: Modifier = Modifier
){
    Icon(
        painter = painterResource(Res.drawable.stepik_logo),
        contentDescription = "stepik logo icon",
        modifier = modifier.size(size),
        tint = MaterialTheme.colorScheme.primary,
    )
}