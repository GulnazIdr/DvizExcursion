package org.example.project.feature.onboarding.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import stepik.composeapp.generated.resources.Res
import stepik.composeapp.generated.resources.dog_bowl
import stepik.composeapp.generated.resources.dumbbells
import stepik.composeapp.generated.resources.game_console
import stepik.composeapp.generated.resources.hearts
import stepik.composeapp.generated.resources.science
import stepik.composeapp.generated.resources.scientific
import org.example.project.core.designsystem.components.StepikLogo
import org.example.project.core.designsystem.components.getWindowHeight
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun CircleOfIcons(){
    Box(
        modifier = Modifier.size(((getWindowHeight() *(0.35)).dp))
    ){
        BoardingCustomCircleIcon(
            resource = Res.drawable.game_console,
            offsetX = (-65).dp,
            modifier = Modifier.align(Alignment.TopCenter)
        )
        BoardingCustomCircleIcon(
            resource = Res.drawable.science,
            modifier = Modifier.align(Alignment.CenterStart)
        )
        BoardingCustomCircleIcon(
            resource = Res.drawable.hearts,
            modifier = Modifier.align(Alignment.CenterEnd)
        )

        BoardingCustomCircleIcon(
            resource = Res.drawable.dog_bowl,
            offsetX = 65.dp,
            modifier = Modifier.align(Alignment.TopCenter)
        )

        BoardingCustomCircleIcon(
            resource = Res.drawable.scientific,
            offsetX = 65.dp,
            modifier = Modifier.align(Alignment.BottomCenter)
        )

        BoardingCustomCircleIcon(
            resource = Res.drawable.dumbbells,
            modifier = Modifier.align(Alignment.BottomCenter),
            offsetX = (-65).dp,
            isDumbbell = true
        )

        StepikLogo(
            size = 80.dp,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun BoardingCustomCircleIcon(
    resource: DrawableResource,
    offsetX: Dp = 0.dp,
    isDumbbell: Boolean = false,
    modifier: Modifier = Modifier
){
    Icon(
        painter = painterResource(resource),
        contentDescription = "icon",
        tint = MaterialTheme.colorScheme.primary,
        modifier = modifier
            .size(
                if (isDumbbell) 60.dp
                else 50.dp
            )
            .offset(x = offsetX)
    )
}

@Preview
@Composable
fun CircleOfIconsPrev(){
    CircleOfIcons()
}